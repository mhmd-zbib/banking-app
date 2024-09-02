package dev.bank.bankingapp.services;

import dev.bank.bankingapp.dto.request.TransferRequest;
import dev.bank.bankingapp.models.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private WalletService walletService;

    @Override
    public void transferFunds(TransferRequest transferRequest) {

        Wallet fromWallet = walletService.getWalletById(transferRequest.getFromWalletId());
        Wallet toWallet = walletService.getWalletById(transferRequest.getToWalletId());

        fromWallet.setBalance(fromWallet.getBalance().subtract(transferRequest.getAmount()));
        toWallet.setBalance(toWallet.getBalance().add(transferRequest.getAmount()));



    }
}
