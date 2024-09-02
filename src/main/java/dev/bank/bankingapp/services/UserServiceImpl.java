package dev.bank.bankingapp.services;

import dev.bank.bankingapp.dto.request.UserRequest;
import dev.bank.bankingapp.exceptions.errors.NotFoundException;
import dev.bank.bankingapp.models.User;
import dev.bank.bankingapp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public User createUser(UserRequest userRequest) {
        User createdUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .username(userRequest.getUsername())
                .phone(userRequest.getPhone())
                .address(userRequest.getAddress())
                .build();

        return userRepository.save(createdUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(getUserById(id));
    }

    @Override
    public User getUserById(Long id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found"));
    }

    @Override
    public Page<User> listUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }

}
