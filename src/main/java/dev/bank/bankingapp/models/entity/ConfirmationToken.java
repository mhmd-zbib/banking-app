package dev.bank.bankingapp.models.entity;

import java.time.LocalDateTime;

public class Token {
    private Long id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
}
