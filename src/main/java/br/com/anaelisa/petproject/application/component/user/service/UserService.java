package br.com.anaelisa.petproject.application.component.user.service;

import br.com.anaelisa.petproject.application.component.user.dto.UserDTO;
import br.com.anaelisa.petproject.application.error.ResourceAlreadyExists;
import br.com.anaelisa.petproject.application.component.user.mapper.UserMapper;
import br.com.anaelisa.petproject.domain.entity.UserEntity;
import br.com.anaelisa.petproject.application.component.user.dto.RegistrationRequestDTO;
import br.com.anaelisa.petproject.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegistrationRequestDTO registrationRequestDTO) {
        if (userRepository.findByUsername(registrationRequestDTO.getUsername()).isPresent()) {
            throw new ResourceAlreadyExists("Username already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(registrationRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequestDTO.getPassword()));

        userRepository.save(user);
    }

    @Transactional
    public List<UserDTO> listAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();

        return UserMapper.INSTANCE.toDtoList(userEntityList);
    }
}
