package dev.bank.bankingapp.services;

import dev.bank.bankingapp.dto.UserDTO;
import dev.bank.bankingapp.exceptions.errors.NotFoundException;
import dev.bank.bankingapp.models.User;
import dev.bank.bankingapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


class ErrorMessages {
    public static final String USER_NOT_FOUND = "User not found";
}

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserDTO user) {
        User newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
        return userRepository.save(newUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(getUserById(id));
    }

    @Override
    public User getUserById(Long id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.USER_NOT_FOUND));
    }

    @Override
    public Page<User> listUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }

}
