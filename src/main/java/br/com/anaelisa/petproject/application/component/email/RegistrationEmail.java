package br.com.anaelisa.petproject.application.component.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationEmail {

    @Value("${spring.mail.username}")
    private final String mailUsername;

    private final JavaMailSender javaMailSender;

    public void sendVerificationEmail(String recipientEmail, String verificationCode) throws MessagingException {

        String htmlContent = "<html><body><h1>Verification Code</h1>"
                + "<p>Your verification code is: <strong>" + verificationCode + "</strong></p>"
                + "</body></html>";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(mailUsername);
        helper.setTo(recipientEmail);
        helper.setSubject("Account Verification");
        helper.setText(htmlContent, true);
        javaMailSender.send(message);

    }
}
