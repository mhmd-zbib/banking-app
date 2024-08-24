package dev.bank.bankingapp.controllers;

import dev.bank.bankingapp.dto.request.TransactionRequest;
import dev.bank.bankingapp.models.Wallet;
import dev.bank.bankingapp.repositories.WalletRepository;
import dev.bank.bankingapp.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;


    @Autowired  //  ONLY FOR TEST
    private WalletRepository walletRepository;

    @PostMapping("/transfer")
    ResponseEntity<String> transferMoney(@RequestBody TransactionRequest transactionRequest) {
        walletService.transferFunds(transactionRequest);
        return ResponseEntity.ok()
                .body("Transfer successful");

    }

    @GetMapping("/all")
    ResponseEntity<List<Wallet>> getAllWalletsTest() {
        List<Wallet> wallets = walletRepository.findAll();
        return ResponseEntity.ok(wallets);
    }

}
