package dev.bank.bankingapp.repositories;

import dev.bank.bankingapp.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
