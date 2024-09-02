package dev.bank.bankingapp.services;

import dev.bank.bankingapp.dto.request.TransferRequest;
import dev.bank.bankingapp.dto.request.WalletRequest;
import dev.bank.bankingapp.enums.WalletStatus;
import dev.bank.bankingapp.exceptions.errors.BadRequestException;
import dev.bank.bankingapp.exceptions.errors.NotFoundException;
import dev.bank.bankingapp.models.User;
import dev.bank.bankingapp.models.Wallet;
import dev.bank.bankingapp.repositories.UserRepository;
import dev.bank.bankingapp.repositories.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

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

    @Override
    public Wallet getWalletById(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(() -> new NotFoundException("Wallet not found"));
    }

    @Override
    public Set<Wallet> getOwnerWallet(Long ownerId) {
        userService.getUserById(ownerId);
        return walletRepository.findAllByOwnerId(ownerId);
    }

    @Override
    public void deleteWallet(Long walletId) {
        Wallet wallet = getWalletById(walletId);
        walletRepository.delete(wallet);
    }

    @Transactional
    @Override
    public void transferFunds(TransferRequest transactionRequest) {

        BigDecimal amount = transactionRequest.getAmount();

        Wallet fromWallet = getWalletById(transactionRequest.getFromWalletId());
        Wallet toWallet = getWalletById(transactionRequest.getToWalletId());

        checkWalletStatus(fromWallet, toWallet);
        validateSufficientFunds(fromWallet, amount);

        BigDecimal fromWalletNewBalance = fromWallet.getBalance().subtract(amount);
        BigDecimal toWalletNewBalance = toWallet.getBalance().add(amount);

        fromWallet.setBalance(fromWalletNewBalance);
        toWallet.setBalance(toWalletNewBalance);

    }

    private void checkWalletStatus(Wallet fromWallet, Wallet toWallet) {
        if (fromWallet.getStatus() == WalletStatus.FROZEN) {
            throw new BadRequestException("Your wallet is frozen");
        }
        if (toWallet.getStatus() == WalletStatus.FROZEN) {
            throw new BadRequestException("Target wallet is frozen");
        }
    }

    private void validateSufficientFunds(Wallet fromWallet, BigDecimal amount) {
        if (fromWallet.getBalance().compareTo(amount) < 0) {
            throw new BadRequestException("Insufficient funds");
        }
    }
}