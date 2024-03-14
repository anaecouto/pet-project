package br.com.anaelisa.petproject.infra.auth.service;

import br.com.anaelisa.petproject.application.error.ResourceAlreadyExists;
import br.com.anaelisa.petproject.domain.entity.UserEntity;
import br.com.anaelisa.petproject.infra.auth.dto.RegistrationRequest;
import br.com.anaelisa.petproject.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegistrationRequest registrationRequest) {
        if (userRepository.findByUsername(registrationRequest.getUsername()).isPresent()) {
            throw new ResourceAlreadyExists("Username already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        userRepository.save(user);
    }
}
