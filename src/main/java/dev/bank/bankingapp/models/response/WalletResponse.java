package dev.bank.bankingapp.models.response;

import dev.bank.bankingapp.models.enums.WalletStatus;
import dev.bank.bankingapp.models.enums.WalletType;

import java.math.BigDecimal;

public record WalletResponse (
         Long id,
         BigDecimal balance,
         WalletStatus status,
         WalletType walletType
){}