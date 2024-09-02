package dev.bank.bankingapp.controllers;

import dev.bank.bankingapp.dto.request.WalletRequest;
import dev.bank.bankingapp.models.Wallet;
import dev.bank.bankingapp.repositories.UserRepository;
import dev.bank.bankingapp.repositories.WalletRepository;
import dev.bank.bankingapp.services.UserServiceImpl;
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

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/all")
    ResponseEntity<List<Wallet>> getAllWalletsTest() {
        List<Wallet> wallets = walletRepository.findAll();
        return ResponseEntity.ok(wallets);
    }

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


}
