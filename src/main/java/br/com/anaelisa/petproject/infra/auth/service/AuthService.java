package br.com.anaelisa.petproject.infra.auth.service;

import br.com.anaelisa.petproject.infra.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    @Qualifier("authenticationManagerBean")
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;

    public String authenticate(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return jwtUtil.generateToken(userDetails);
    }
}

