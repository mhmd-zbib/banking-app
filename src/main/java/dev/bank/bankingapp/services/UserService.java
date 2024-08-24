package dev.bank.bankingapp.services;

import dev.bank.bankingapp.dto.request.UserRequest;
import dev.bank.bankingapp.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User createUser(UserRequest user);

    void deleteUser(Long id);

    User getUserById(Long id);

    Page<User> listUsers(Pageable pageable);

}
