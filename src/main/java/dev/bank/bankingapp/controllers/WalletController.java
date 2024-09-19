package dev.bank.bankingapp.controllers;

import dev.bank.bankingapp.models.request.TransferRequest;
import dev.bank.bankingapp.models.request.WalletRequest;
import dev.bank.bankingapp.models.entity.Transfer;
import dev.bank.bankingapp.models.entity.Wallet;
import dev.bank.bankingapp.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;




    @GetMapping("/{id}")
    ResponseEntity<Wallet> getWalletById(@PathVariable Long id) {
        Wallet wallet = walletService.getWalletById(id);
        return ResponseEntity.ok(wallet);
    }

    @PostMapping
    ResponseEntity<Wallet> createWallet(@RequestBody WalletRequest walletRequest) {
        Wallet wallet = walletService.createWallet(walletRequest);
        return ResponseEntity.ok(wallet);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteWallet(@PathVariable Long id) {
        walletService.deleteWallet(id);
        return ResponseEntity.ok("Wallet has been deleted successfully");
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody TransferRequest transferRequest) {
        Transfer transfer = walletService.transferFunds(transferRequest);
        return ResponseEntity.ok(transfer);
    }


}
