package dev.bank.bankingapp.controllers;

import dev.bank.bankingapp.exceptions.errors.UnAuthorizedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class Test {


    @GetMapping
    public ResponseEntity<Void> testing() {
        throw new UnAuthorizedException("sfa");
    }


}
