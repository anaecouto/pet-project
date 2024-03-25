package br.com.anaelisa.petproject.application.component;

import br.com.anaelisa.petproject.application.component.customer.dto.CustomerDTO;
import br.com.anaelisa.petproject.application.component.customer.service.CustomerService;
import br.com.anaelisa.petproject.domain.entity.CustomerEntity;
import br.com.anaelisa.petproject.domain.entity.PetEntity;
import br.com.anaelisa.petproject.infra.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

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

