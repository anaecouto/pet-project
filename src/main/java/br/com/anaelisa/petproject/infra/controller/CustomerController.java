package br.com.anaelisa.petproject.infra.controller;

import br.com.anaelisa.petproject.application.component.customer.dto.CustomerDTO;
import br.com.anaelisa.petproject.application.component.customer.service.CustomerService;
import br.com.anaelisa.petproject.infra.helper.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
@ResponseBody
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<CustomerDTO>>> listCustomers() {
        List<CustomerDTO> users = customerService.listAllCustomers();
        ApiResponse<List<CustomerDTO>> apiResponse = new ApiResponse<>("SUCCESS", users, 200L, null);
        return ResponseEntity.status(200).body(apiResponse);
    }
}
