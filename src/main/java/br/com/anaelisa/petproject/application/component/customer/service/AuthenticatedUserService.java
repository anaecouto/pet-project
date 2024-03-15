package br.com.anaelisa.petproject.application.component.customer.service;

import br.com.anaelisa.petproject.domain.entity.CustomerEntity;
import br.com.anaelisa.petproject.infra.repository.CustomerRepository;
import br.com.anaelisa.petproject.infra.security.implementation.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticatedUserService {

    private final CustomerRepository customerRepository;

    public CustomerEntity getLoggedUser() {
        String username = UserDetailsImpl.getUsernameFromAuthenticatedUser();
        CustomerEntity customerEntity = customerRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return customerEntity;
    }
}
