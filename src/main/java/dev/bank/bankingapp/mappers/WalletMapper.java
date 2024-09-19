package dev.bank.bankingapp.mappers;

import dev.bank.bankingapp.models.response.WalletResponse;
import dev.bank.bankingapp.models.entity.Wallet;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {

    public WalletResponse toResponse(Wallet wallet) {
        if (wallet == null) return null;

        return new WalletResponse(
                wallet.getId(),
                wallet.getBalance(),
                wallet.getStatus(),
                wallet.getWalletType()
        );
    }
//
//    public Wallet toEntity(WalletResponse walletResponse) {}

}
