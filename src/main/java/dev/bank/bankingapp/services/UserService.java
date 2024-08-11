package dev.bank.bankingapp.services;

import dev.bank.bankingapp.dto.UserDTO;
import dev.bank.bankingapp.models.User;

public interface UserService {

    User createUser(UserDTO user);

    User getUserByUsername(String username);

    void deleteUser(String username);

    User updateUser(User user);

    User getUserById(int id);


}
