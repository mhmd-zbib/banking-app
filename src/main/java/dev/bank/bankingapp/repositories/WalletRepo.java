package dev.bank.bankingapp.repositories;

import dev.bank.bankingapp.models.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Long> {
    Set<Wallet> findAllByOwnerId(Long id);
}
