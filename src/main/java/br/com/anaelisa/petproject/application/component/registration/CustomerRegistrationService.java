package br.com.anaelisa.petproject.application.component.registration;

import br.com.anaelisa.petproject.application.component.auth.enums.EnumRoles;
import br.com.anaelisa.petproject.application.component.customer.dto.RegistrationRequestDTO;
import br.com.anaelisa.petproject.application.component.customer.service.AuthenticatedUserService;
import br.com.anaelisa.petproject.application.error.ResourceAlreadyExists;
import br.com.anaelisa.petproject.application.error.VerificationCodeError;
import br.com.anaelisa.petproject.application.utils.GenerateCode;
import br.com.anaelisa.petproject.domain.entity.CustomerEntity;
import br.com.anaelisa.petproject.infra.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerRegistrationService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticatedUserService authenticatedUserService;

    @Transactional
    public void register(RegistrationRequestDTO registrationRequestDTO) {
        if (customerRepository.findByUsername(registrationRequestDTO.getUsername()).isPresent()) {
            throw new ResourceAlreadyExists("Username already exists");
        }

        CustomerEntity customer = new CustomerEntity();
        customer.setUsername(registrationRequestDTO.getUsername());
        customer.setPassword(passwordEncoder.encode(registrationRequestDTO.getPassword()));
        customer.setName(registrationRequestDTO.getName());

        String verificationCode = GenerateCode.VERIFICATION_CODE.execute();
        customer.setVerificationCode(verificationCode);

        customerRepository.save(customer);
    }

    @Transactional
    public String verify(String code) {
        CustomerEntity customerEntity = authenticatedUserService.getLoggedUser();

        if (customerEntity.isEnable()) {
            return "Customer already verified";
        }

        if (!Objects.equals(customerEntity.getVerificationCode(), code)) {
            throw new VerificationCodeError("Verification code is not valid, please check your e-mail");
        }

        customerEntity.setRole(EnumRoles.USER.getId());
        customerEntity.setEnable(true);

        customerRepository.save(customerEntity);

        return "Customer has been verified successfully!";
    }
}
