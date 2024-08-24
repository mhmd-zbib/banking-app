package dev.bank.bankingapp.services;

import dev.bank.bankingapp.dto.request.TransactionRequest;
import dev.bank.bankingapp.dto.response.EntityResponse;
import dev.bank.bankingapp.enums.WalletStatus;
import dev.bank.bankingapp.exceptions.errors.BadRequestException;
import dev.bank.bankingapp.exceptions.errors.NotFoundException;
import dev.bank.bankingapp.models.Wallet;
import dev.bank.bankingapp.repositories.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Transactional
    @Override
    public EntityResponse createWallet(Long userId) {
        Wallet wallet = Wallet.builder().id(userId).balance(BigDecimal.ZERO).status(WalletStatus.ACTIVE).build();
        walletRepository.save(wallet);
        return null;
    }

    @Override
    public Wallet getWallet(Long userId) {
        return walletRepository.findById(userId).orElseThrow(() -> new NotFoundException("Wallet doesn't exist"));
    }

    @Override
    public EntityResponse updateWallet(Wallet wallet) {
        Wallet foundWallet = getWallet(wallet.getId());
        walletRepository.save(wallet);
        return EntityResponse.builder().id(foundWallet.getId()).build();

    }

    @Transactional
    @Override
    public EntityResponse deleteWallet(Long userId) {
        return null;
    }

    @Transactional
    @Override
    public EntityResponse transferFunds(TransactionRequest transactionRequest) {

        BigDecimal amount = transactionRequest.getAmount();

        Wallet fromWallet = getWallet(transactionRequest.getFromWalletId());
        Wallet toWallet = getWallet(transactionRequest.getToWalletId());

        checkWalletStatus(fromWallet, toWallet);
        validateSufficientFunds(fromWallet, amount);

        BigDecimal fromWalletNewBalance = fromWallet.getBalance().subtract(amount);
        BigDecimal toWalletNewBalance = toWallet.getBalance().add(amount);

        fromWallet.setBalance(fromWalletNewBalance);
        toWallet.setBalance(toWalletNewBalance);

        updateWallet(fromWallet);
        updateWallet(toWallet);

        return null;
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