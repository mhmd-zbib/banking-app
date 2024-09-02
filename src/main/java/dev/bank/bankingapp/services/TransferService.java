package dev.bank.bankingapp.services;

import dev.bank.bankingapp.dto.request.TransferRequest;

public interface TransferService {
    void transferFunds(TransferRequest transferRequest);
}
