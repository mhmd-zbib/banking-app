package dev.bank.bankingapp.repositories;

import dev.bank.bankingapp.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    public User findByUsername(String username);

    public Optional<User> findByEmail(String email);
}
