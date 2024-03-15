package br.com.anaelisa.petproject.infra.controller;

import br.com.anaelisa.petproject.application.component.user.dto.UserDTO;
import br.com.anaelisa.petproject.application.component.user.dto.RegistrationRequestDTO;
import br.com.anaelisa.petproject.application.component.user.service.UserService;
import br.com.anaelisa.petproject.infra.helper.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerUser(@Valid @RequestBody RegistrationRequestDTO registrationRequestDTO) {
            userService.register(registrationRequestDTO);
            ApiResponse<String> apiResponse = new ApiResponse<>("SUCCESS", "User registered successfully", 200L, null);
            return ResponseEntity.status(200).body(apiResponse);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<UserDTO>>> listUsers() {
        List<UserDTO> users = userService.listAllUsers();
        ApiResponse<List<UserDTO>> apiResponse = new ApiResponse<>("SUCCESS", users, 200L, null);
        return ResponseEntity.status(200).body(apiResponse);
    }
}
