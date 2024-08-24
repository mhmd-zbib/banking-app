package dev.bank.bankingapp.services;

import dev.bank.bankingapp.dto.request.TransactionRequest;
import dev.bank.bankingapp.dto.response.EntityResponse;
import dev.bank.bankingapp.models.Wallet;

public interface WalletService {

    EntityResponse createWallet(Long userId);

    Wallet getWallet(Long userId);

    EntityResponse updateWallet(Wallet wallet);

    EntityResponse deleteWallet(Long userId);

    EntityResponse transferFunds(TransactionRequest transactionRequest);

}
