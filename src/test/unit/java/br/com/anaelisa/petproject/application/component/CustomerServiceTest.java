package br.com.anaelisa.petproject.application.component;

import br.com.anaelisa.petproject.application.component.customer.dto.CustomerDTO;
import br.com.anaelisa.petproject.application.component.customer.dto.RegistrationRequestDTO;
import br.com.anaelisa.petproject.application.component.customer.service.CustomerService;
import br.com.anaelisa.petproject.application.component.pet.dto.PetDTO;
import br.com.anaelisa.petproject.application.error.ResourceAlreadyExists;
import br.com.anaelisa.petproject.domain.entity.CustomerEntity;
import br.com.anaelisa.petproject.domain.entity.PetEntity;
import br.com.anaelisa.petproject.infra.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void shouldRegisterNewCustomer() {
        RegistrationRequestDTO requestDTO = RegistrationRequestDTO.builder()
                .username("username")
                .password("password")
                .build();

        when(customerRepository.findByUsername("username")).thenReturn(Optional.empty());

        customerService.register(requestDTO);

        verify(customerRepository, times(1)).save(any(CustomerEntity.class));
    }

    @Test
    void shouldThrowErrorIfRegisteringExistingCustomer() {
        RegistrationRequestDTO requestDTO = RegistrationRequestDTO.builder()
                .username("username")
                .password("password")
                .build();

        when(customerRepository.findByUsername("username")).thenReturn(Optional.of(new CustomerEntity()));

        ResourceAlreadyExists exception = assertThrows(ResourceAlreadyExists.class, () -> customerService.register(requestDTO));

        assertEquals("Username already exists", exception.getMessage());
        verify(customerRepository, never()).save(any(CustomerEntity.class));
    }

    @Test
    void shouldListAllCustomers() {
        CustomerEntity customer = new CustomerEntity();
        customer.setId(1L);
        customer.setUsername("user");
        customer.setPassword("password");

        PetEntity pet = new PetEntity();
        pet.setId(1L);
        pet.setName("name");

        customer.setPetList(List.of(pet));

        when(customerRepository.findAll()).thenReturn(Collections.singletonList(customer));

        List<CustomerDTO> result = customerService.listAllCustomers();

        assertEquals(1, result.size());
        assertEquals("user", result.get(0).getUsername());
        assertEquals(1, result.get(0).getPetList().size());
    }
}

