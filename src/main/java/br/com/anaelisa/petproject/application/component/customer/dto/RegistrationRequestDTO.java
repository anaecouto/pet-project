package br.com.anaelisa.petproject.application.component.customer.dto;

import br.com.anaelisa.petproject.domain.validator.ValidPassword;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationRequestDTO {

    private String name;

    private String username;

    @ValidPassword
    private String password;
}
