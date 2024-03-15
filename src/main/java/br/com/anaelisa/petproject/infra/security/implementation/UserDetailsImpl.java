package br.com.anaelisa.petproject.infra.security.implementation;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserDetailsImpl implements UserDetails {

    private long id;
    private String username;
    private String senha;
    private GrantedAuthority authority;

    public UserDetailsImpl() {}

    public UserDetailsImpl(long id, String username, String senha, String authority) {
        String role = String.format("ROLE_%s", authority.toUpperCase());

        this.id = id;
        this.username = username;
        this.senha = senha;
        this.authority = new SimpleGrantedAuthority(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(authority).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static String getUsernameFromAuthenticatedUser() {
        Optional<Authentication> currentAuthentication = Optional.ofNullable(SecurityContextHolder
                .getContext()
                .getAuthentication());

        Optional<String> principalString = currentAuthentication.map(Authentication::getPrincipal)
                .map(p -> p instanceof UserDetails ? ((UserDetails) p).getUsername() : p.toString());

        return principalString.orElse("<<no-user>>");
    }
}
