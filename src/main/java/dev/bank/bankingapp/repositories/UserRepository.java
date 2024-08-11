package dev.bank.bankingapp.repositories;

import dev.bank.bankingapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
