package dev.bank.bankingapp.models.response;

import dev.bank.bankingapp.enums.WalletStatus;
import dev.bank.bankingapp.enums.WalletType;

import java.math.BigDecimal;

public record WalletResponse (
         Long id,
         BigDecimal balance,
         WalletStatus status,
         WalletType walletType
){}