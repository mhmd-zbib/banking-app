package dev.bank.bankingapp.controllers;


import dev.bank.bankingapp.models.entity.User;
import dev.bank.bankingapp.models.entity.Wallet;
import dev.bank.bankingapp.models.request.UserRequest;
import dev.bank.bankingapp.services.UserService;
import dev.bank.bankingapp.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final WalletService walletService;

    @Autowired
    public UserController(UserService userService, WalletService walletService) {
        this.userService = userService;
        this.walletService = walletService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<Page<User>> listUsers(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page,
                size);
        Page<User> users = userService.listUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/{id}/wallets")
    public ResponseEntity<Set<Wallet>> getUserWallet(@PathVariable Long id) {
        Set<Wallet> wallets = walletService.getOwnerWallets(id);
        return ResponseEntity.ok(wallets);
    }

}


