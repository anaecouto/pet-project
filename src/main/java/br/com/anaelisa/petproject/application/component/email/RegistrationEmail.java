package br.com.anaelisa.petproject.application.component.email;

import br.com.anaelisa.petproject.application.component.customer.dto.RegistrationRequestDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;

@Component
@RequiredArgsConstructor
public class RegistrationEmail {

    @Value("${spring.mail.username}")
    private String mailUsername;

    private final JavaMailSender javaMailSender;

    private final ResourceLoader resourceLoader;

    @Value("classpath:templates/email/VerificationCode.html")
    private Resource resource;

    @Async
    public void sendVerificationEmailAsync(RegistrationRequestDTO registrationRequestDTO, String verificationCode)
            throws MessagingException, IOException {
        sendVerificationEmail(registrationRequestDTO, verificationCode);
    }

    public void sendVerificationEmail(RegistrationRequestDTO registrationRequestDTO, String verificationCode)
            throws MessagingException, IOException
    {
        String htmlContent = Files.readString(resource.getFile().toPath());

        htmlContent = htmlContent
                .replace("${verificationCode}", verificationCode)
                .replace("${userName}", registrationRequestDTO.getName());

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(mailUsername);
        helper.setTo(registrationRequestDTO.getUsername());
        helper.setSubject("Account Verification");
        helper.setText(htmlContent, true);
        javaMailSender.send(message);

    }
}
