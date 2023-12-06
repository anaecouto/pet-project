package br.com.anaelisa.petproject.infra.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class GeneralController {

    @GetMapping("")
    public String sayWelcome() {
        return "Welcome to your pet project!";
    }
}
