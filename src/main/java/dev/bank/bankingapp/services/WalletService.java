package dev.bank.bankingapp.services;

import dev.bank.bankingapp.dto.request.TransferRequest;
import dev.bank.bankingapp.dto.request.WalletRequest;
import dev.bank.bankingapp.models.Wallet;

import java.util.Set;

public interface WalletService {

    Wallet createWallet(WalletRequest walletRequest);

    Wallet getWalletById(Long walletId);

//    Wallet updateWallet(WalletRequest walletRequest);

    Set<Wallet> getOwnerWallet(Long ownerId);

    void transferFunds(TransferRequest transactionRequest);

    void deleteWallet(Long walletId);

}
