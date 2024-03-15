package br.com.anaelisa.petproject.application.component.auth.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponseDTO {
    private String access_token;

    public JwtAuthenticationResponseDTO(String access_token) {
        this.access_token = access_token;
    }
}

