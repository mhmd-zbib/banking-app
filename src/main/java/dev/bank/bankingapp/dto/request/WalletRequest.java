package dev.bank.bankingapp.dto.request;

import dev.bank.bankingapp.enums.WalletType;
import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletRequest {
    private WalletType walletType;
    private Long userId;

}
