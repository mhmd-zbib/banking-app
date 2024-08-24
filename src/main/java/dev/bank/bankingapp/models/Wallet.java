package dev.bank.bankingapp.models;

import dev.bank.bankingapp.enums.WalletStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet {

    @Id
    private Long id;

    private BigDecimal balance = BigDecimal.ZERO;

    private WalletStatus status = WalletStatus.ACTIVE;

}
