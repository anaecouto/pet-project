package br.com.anaelisa.petproject.application.component.user.service;

import br.com.anaelisa.petproject.domain.entity.UserEntity;
import br.com.anaelisa.petproject.infra.repository.UserRepository;
import br.com.anaelisa.petproject.infra.security.implementation.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticatedUserService {

    private final UserRepository userRepository;

    public UserEntity getLoggedUser() {
        String username = UserDetailsImpl.getUsernameFromAuthenticatedUser();
        UserEntity userEntity = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return userEntity;
    }
}
