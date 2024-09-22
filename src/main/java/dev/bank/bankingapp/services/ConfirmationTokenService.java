package dev.bank.bankingapp.services;


import dev.bank.bankingapp.models.entity.ConfirmationToken;
import dev.bank.bankingapp.repositories.ConfirmationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepo confirmationTokenRepo;

    @Autowired
    public ConfirmationTokenService(ConfirmationTokenRepo confirmationTokenRepo) {
        this.confirmationTokenRepo = confirmationTokenRepo;
    }

    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepo.save(confirmationToken);
    }

}
