package dev.bank.bankingapp.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.bank.bankingapp.enums.WalletStatus;
import dev.bank.bankingapp.enums.WalletType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal balance = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private WalletType walletType;

    @Enumerated(EnumType.STRING)
    private WalletStatus status = WalletStatus.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonIgnore
    private User owner;

    @OneToMany(mappedBy = "fromWallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Transfer> sentTransfers;

    @OneToMany(mappedBy = "toWallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Transfer> recievedTransfers;

}
