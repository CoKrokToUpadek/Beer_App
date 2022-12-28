package com.cokroktoupadek.beersandmealsbackend.h2_test_db.service.email;

import com.cokroktoupadek.beersandmealsbackend.client.config.Config;
import com.cokroktoupadek.beersandmealsbackend.service.email.SimpleEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SimpleEmailServiceTest {
    @InjectMocks
    private SimpleEmailService simpleEmailService;
    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    Config config;

    @Test
    public void shouldSendEmail() {

        when(config.getAdminMail()).thenReturn("test@test.com");

        //Given
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(config.getAdminMail());
        mailMessage.setSubject("Db update Status");
        mailMessage.setText("ok");
        //When
        simpleEmailService.send("ok");
        //Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }
}
