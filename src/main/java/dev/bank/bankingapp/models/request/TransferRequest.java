package dev.bank.bankingapp.models.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private BigDecimal amount;

    private String description;

    @NotBlank
    private Long fromWalletId;

    @NotBlank
    private Long toWalletId;

    private String type;
}
