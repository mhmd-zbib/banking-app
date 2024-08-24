package dev.bank.bankingapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.mapping.Any;

@Data
@AllArgsConstructor
public class ApiResponse {

    private final String message;
    private final Any data;
    private Long timestamp;

    public ApiResponse(Long timestamp,
                       String message) {
        this.timestamp = timestamp;
        this.message = message;
        this.data = null;
    }

}
