package br.com.anaelisa.petproject.infra.controller;

import br.com.anaelisa.petproject.application.component.customer.dto.CustomerDTO;
import br.com.anaelisa.petproject.application.component.customer.dto.RegistrationRequestDTO;
import br.com.anaelisa.petproject.application.component.customer.service.CustomerService;
import br.com.anaelisa.petproject.infra.helper.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerCustomer(@Valid @RequestBody RegistrationRequestDTO registrationRequestDTO) {
            customerService.register(registrationRequestDTO);
            ApiResponse<String> apiResponse = new ApiResponse<>("SUCCESS", "User registered successfully", 200L, null);
            return ResponseEntity.status(200).body(apiResponse);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<CustomerDTO>>> listCustomers() {
        List<CustomerDTO> users = customerService.listAllUsers();
        ApiResponse<List<CustomerDTO>> apiResponse = new ApiResponse<>("SUCCESS", users, 200L, null);
        return ResponseEntity.status(200).body(apiResponse);
    }
}
