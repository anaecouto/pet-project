package br.com.anaelisa.petproject.infra.controller;

import br.com.anaelisa.petproject.infra.auth.dto.JwtAuthenticationResponse;
import br.com.anaelisa.petproject.infra.auth.dto.LoginRequest;
import br.com.anaelisa.petproject.infra.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        String jwt = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
}

