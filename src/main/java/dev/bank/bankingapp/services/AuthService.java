package dev.bank.bankingapp.services;

import dev.bank.bankingapp.exceptions.errors.BadRequestException;
import dev.bank.bankingapp.models.entity.User;
import dev.bank.bankingapp.models.enums.UserRole;
import dev.bank.bankingapp.models.request.RegistrationRequest;
import dev.bank.bankingapp.utils.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    @Autowired
    public AuthService(UserService userService, EmailValidator emailValidator) {
        this.userService = userService;
        this.emailValidator = emailValidator;
    }

    public String register(RegistrationRequest request) {
        boolean isValid = emailValidator.test(request.getEmail());
        if (!isValid) {
            throw new BadRequestException("Invalid email address");
        }
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .username(request.getUsername())
                .userRole(UserRole.USER)
                .build();
        userService.createUser(user);
        return "works";
    }


}
