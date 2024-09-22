package dev.bank.bankingapp.models.request;

import dev.bank.bankingapp.models.enums.WalletStatus;
import dev.bank.bankingapp.models.enums.WalletType;
import lombok.*;

import java.math.BigDecimal;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletRequest {

    private Long userId;
    private WalletType walletType;
    private Long id;
    private WalletStatus status;
    private BigDecimal balance;

}
