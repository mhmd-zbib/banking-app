package dev.bank.bankingapp.repositories;

import dev.bank.bankingapp.models.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepo extends JpaRepository<Transfer, Long> {
}
