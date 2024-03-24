package br.com.anaelisa.petproject.application.component;

import br.com.anaelisa.petproject.application.component.auth.service.AuthService;
import br.com.anaelisa.petproject.infra.security.implementation.UserDetailsServiceImpl;
import br.com.anaelisa.petproject.infra.security.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @InjectMocks
    private AuthService authService;

    @Test
    void shouldAuthenticate() {

        String username = "testUser";
        String password = "testPassword";
        String token = "generatedToken";

        UserDetails userDetails = User.withUsername(username).password(password).build();
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

        when(authenticationManager.authenticate(any()))
                .thenReturn(authentication);
        when(userDetailsService.loadUserByUsername(username))
                .thenReturn(userDetails);
        when(jwtUtil.generateToken(userDetails))
                .thenReturn(token);

        String generatedToken = authService.authenticate(username, password);

        assertEquals(token, generatedToken);

        verify(authenticationManager, times(1)).authenticate(any());
        verify(userDetailsService, times(1)).loadUserByUsername(username);
        verify(jwtUtil, times(1)).generateToken(userDetails);
    }
}
