package br.com.anaelisa.petproject.application.component.customer.service;

import br.com.anaelisa.petproject.application.component.customer.dto.CustomerDTO;
import br.com.anaelisa.petproject.application.error.ResourceAlreadyExists;
import br.com.anaelisa.petproject.application.component.customer.mapper.CustomerMapper;
import br.com.anaelisa.petproject.domain.entity.CustomerEntity;
import br.com.anaelisa.petproject.application.component.customer.dto.RegistrationRequestDTO;
import br.com.anaelisa.petproject.infra.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegistrationRequestDTO registrationRequestDTO) {
        if (customerRepository.findByUsername(registrationRequestDTO.getUsername()).isPresent()) {
            throw new ResourceAlreadyExists("Username already exists");
        }

        CustomerEntity user = new CustomerEntity();
        user.setUsername(registrationRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequestDTO.getPassword()));

        customerRepository.save(user);
    }

    @Transactional
    public List<CustomerDTO> listAllUsers() {
        List<CustomerEntity> customerEntityList = customerRepository.findAll();

        return CustomerMapper.INSTANCE.toDtoList(customerEntityList);
    }
}
