package br.com.anaelisa.petproject.infra.controller;

import br.com.anaelisa.petproject.application.component.auth.dto.JwtAuthenticationResponseDTO;
import br.com.anaelisa.petproject.application.component.auth.dto.LoginRequestDTO;
import br.com.anaelisa.petproject.application.component.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        String jwt = authService.authenticate(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponseDTO(jwt));
    }
}

