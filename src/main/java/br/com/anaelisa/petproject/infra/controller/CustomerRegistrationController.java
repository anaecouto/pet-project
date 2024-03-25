package br.com.anaelisa.petproject.infra.controller;

import br.com.anaelisa.petproject.application.component.customer.dto.RegistrationRequestDTO;
import br.com.anaelisa.petproject.application.component.registration.CustomerRegistrationService;
import br.com.anaelisa.petproject.infra.helper.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class CustomerRegistrationController {

    private final CustomerRegistrationService customerRegistrationService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<String>> registerCustomer(@Valid @RequestBody RegistrationRequestDTO registrationRequestDTO) throws MessagingException {
        customerRegistrationService.register(registrationRequestDTO);
        ApiResponse<String> apiResponse = new ApiResponse<>("SUCCESS", "User registered successfully", 200L, null);
        return ResponseEntity.status(200).body(apiResponse);
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<String>> verifyCustomer(@Param("code") String code) {
        String message = customerRegistrationService.verify(code);
        ApiResponse<String> apiResponse = new ApiResponse<>("SUCCESS", message, 200L, null);
        return ResponseEntity.status(200).body(apiResponse);
    }
}
