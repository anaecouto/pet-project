package br.com.anaelisa.petproject.infra.controller;

import br.com.anaelisa.petproject.application.dto.PetDTO;
import br.com.anaelisa.petproject.infra.auth.dto.RegistrationRequest;
import br.com.anaelisa.petproject.infra.auth.service.UserService;
import br.com.anaelisa.petproject.infra.helper.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody RegistrationRequest registrationRequest) {
            userService.register(registrationRequest);
            ApiResponse<String> apiResponse = new ApiResponse<>("SUCCESS", "User registered successfully", 200L, null);
            return ResponseEntity.status(200).body(apiResponse);
    }
}
