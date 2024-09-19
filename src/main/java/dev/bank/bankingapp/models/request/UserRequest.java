package dev.bank.bankingapp.models.request;

import dev.bank.bankingapp.models.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private String phone;
    private Address address;


}
