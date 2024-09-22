package dev.bank.bankingapp.services;

import dev.bank.bankingapp.models.request.TransferRequest;
import dev.bank.bankingapp.models.request.WalletRequest;
import dev.bank.bankingapp.models.enums.WalletStatus;
import dev.bank.bankingapp.exceptions.errors.BadRequestException;
import dev.bank.bankingapp.exceptions.errors.NotFoundException;
import dev.bank.bankingapp.models.entity.Transfer;
import dev.bank.bankingapp.models.entity.User;
import dev.bank.bankingapp.models.entity.Wallet;
import dev.bank.bankingapp.repositories.TransferRepo;
import dev.bank.bankingapp.repositories.UserRepo;
import dev.bank.bankingapp.repositories.WalletRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class WalletService {

    @Autowired
    private WalletRepo walletRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private TransferRepo transferRepository;

    @Transactional
    public Wallet createWallet(WalletRequest walletRequest) {
        User user = userService.getUserById(walletRequest.getUserId());
        Wallet wallet = Wallet.builder()
                .walletType(walletRequest.getWalletType())
                .balance(BigDecimal.ZERO)
                .status(WalletStatus.ACTIVE)
                .owner(user)
                .build();
        walletRepository.save(wallet);
        return wallet;
    }

     public Wallet getWalletById(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(
                () -> new NotFoundException("Wallet not found"));
    }

     @Transactional
    public Wallet updateWallet(WalletRequest walletRequest) {

        Wallet existingWallet = getWalletById(walletRequest.getId());

        if (walletRequest.getBalance() != null) {
            existingWallet.setBalance(walletRequest.getBalance());
        }
        if (walletRequest.getStatus() != null) {
            existingWallet.setStatus(walletRequest.getStatus());
        }
        if (walletRequest.getWalletType() != null) {
            existingWallet.setWalletType(walletRequest.getWalletType());
        }
        return walletRepository.save(existingWallet);
    }


     public Set<Wallet> getOwnerWallets(Long ownerId) {
        userService.getUserById(ownerId);
        return walletRepository.findAllByOwnerId(ownerId);
    }

     public void deleteWallet(Long walletId) {
        Wallet wallet = getWalletById(walletId);
        walletRepository.delete(wallet);
    }


     public Transfer transferFunds(TransferRequest transferRequest) {

        BigDecimal amount = transferRequest.getAmount();
        Wallet fromWallet = getWalletById(transferRequest.getFromWalletId());
        Wallet toWallet = getWalletById(transferRequest.getToWalletId());

        validateTransfer(amount, fromWallet, toWallet);

        fromWallet.setBalance(fromWallet.getBalance().subtract(amount));
        toWallet.setBalance(toWallet.getBalance().add(amount));

        walletRepository.save(fromWallet);
        walletRepository.save(toWallet);

        Transfer transfer = Transfer.builder()
                .fromWallet(fromWallet)
                .toWallet(toWallet)
                .amount(amount)
                .type(transferRequest.getType())
                .description(transferRequest.getDescription())
                .build();

        return transferRepository.save(transfer);

    }


    private void validateTransfer(BigDecimal amount, Wallet fromWallet, Wallet toWallet) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Invalid amount. Amount must be greater than zero.");
        }

        if (amount.compareTo(fromWallet.getBalance()) > 0) {
            throw new BadRequestException("Insufficient funds. Your balance is insufficient for this transfer.");
        }

        if (fromWallet.getStatus().equals(WalletStatus.FROZEN)) {
            throw new BadRequestException("Your wallet is frozen and cannot perform transfers.");
        }

        if (toWallet.getStatus().equals(WalletStatus.FROZEN)) {
            throw new BadRequestException("The recipient's wallet is frozen and cannot receive funds.");
        }
    }

}