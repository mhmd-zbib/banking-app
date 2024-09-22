package dev.bank.bankingapp.controllers;

import dev.bank.bankingapp.models.request.RegistrationRequest;
import dev.bank.bankingapp.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService registrationService;

    @Autowired
    public AuthController(AuthService registrationService) {
        this.registrationService = registrationService;
    }


    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
}
