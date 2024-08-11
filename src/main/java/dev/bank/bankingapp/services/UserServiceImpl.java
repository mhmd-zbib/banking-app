package dev.bank.bankingapp.services;

import dev.bank.bankingapp.dto.UserDTO;
import dev.bank.bankingapp.models.User;
import dev.bank.bankingapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserDTO user) {
        User newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .username(user.getUsername())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
        return userRepository.save(newUser);
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }
}
