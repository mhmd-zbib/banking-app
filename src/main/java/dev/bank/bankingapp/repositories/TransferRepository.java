package dev.bank.bankingapp.repositories;

import dev.bank.bankingapp.models.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
