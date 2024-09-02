package dev.bank.bankingapp.repositories;

import dev.bank.bankingapp.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Set<Wallet> findAllByOwnerId(Long id);
}
