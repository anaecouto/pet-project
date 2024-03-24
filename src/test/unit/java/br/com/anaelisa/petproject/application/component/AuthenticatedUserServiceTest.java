package br.com.anaelisa.petproject.application.component;

import br.com.anaelisa.petproject.application.component.customer.service.AuthenticatedUserService;
import br.com.anaelisa.petproject.domain.entity.CustomerEntity;
import br.com.anaelisa.petproject.infra.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticatedUserServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private AuthenticatedUserService authenticatedUserService;

    @Test
    void testGetLoggedUser() {
        String username = "testuser";
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setUsername(username);

        when(customerRepository.findByUsername(anyString())).thenReturn(Optional.of(customerEntity));

        CustomerEntity result = authenticatedUserService.getLoggedUser();

        assertNotNull(result);
        assertEquals(username, result.getUsername());

        verify(customerRepository, times(1)).findByUsername(anyString());
    }
}

