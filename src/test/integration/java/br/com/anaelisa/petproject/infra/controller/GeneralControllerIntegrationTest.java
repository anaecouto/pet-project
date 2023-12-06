package br.com.anaelisa.petproject.infra.controller;

import br.com.anaelisa.petproject.BaseIntegrationTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GeneralControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    void healthCheck() {
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/health",
                String.class)).contains("Welcome to your pet project!");
    }
}
