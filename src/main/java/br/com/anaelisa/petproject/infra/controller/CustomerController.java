package br.com.anaelisa.petproject.infra.controller;

import br.com.anaelisa.petproject.application.component.customer.dto.CustomerDTO;
import br.com.anaelisa.petproject.application.component.customer.service.CustomerService;
import br.com.anaelisa.petproject.infra.helper.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<CustomerDTO>>> listCustomers() {
        List<CustomerDTO> users = customerService.listAllCustomers();
        ApiResponse<List<CustomerDTO>> apiResponse = new ApiResponse<>("SUCCESS", users, 200L, null);
        return ResponseEntity.status(200).body(apiResponse);
    }
}
