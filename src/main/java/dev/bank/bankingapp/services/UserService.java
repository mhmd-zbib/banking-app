package dev.bank.bankingapp.services;

import dev.bank.bankingapp.exceptions.errors.BadRequestException;
import dev.bank.bankingapp.exceptions.errors.NotFoundException;
import dev.bank.bankingapp.models.entity.User;
import dev.bank.bankingapp.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepo userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws NotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
    }

    @Transactional
    public User createUser(User user) {
        boolean userEmailExists = userRepository.findByEmail(user.getEmail()).isPresent();
        boolean userUsernameExists = userRepository.findByUsername(user.getUsername()).isPresent();

        if (userEmailExists) {
            throw new BadRequestException("Email already exists");
        }
        if (userUsernameExists) {
            throw new BadRequestException("Username already exists");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // TODO: Send confirmation token

        return userRepository.save(user);
    }


    public void deleteUser(Long id) {
        userRepository.delete(getUserById(id));
    }


    public User getUserById(Long id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found"));
    }


    public Page<User> listUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }


}
