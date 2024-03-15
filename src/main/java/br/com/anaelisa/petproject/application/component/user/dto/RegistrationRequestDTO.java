package br.com.anaelisa.petproject.application.component.user.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegistrationRequestDTO {

    private String username;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must be at least 8 characters long and contain at least one letter, one digit and one special character")
    private String password;
}
