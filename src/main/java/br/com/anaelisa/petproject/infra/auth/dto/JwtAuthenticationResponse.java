package br.com.anaelisa.petproject.infra.auth.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String access_token;

    public JwtAuthenticationResponse(String access_token) {
        this.access_token = access_token;
    }
}

