package api.security.auth.domain.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import api.security.auth.domain.usecase.SendEmailUseCase;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SendEmailService implements SendEmailUseCase {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;


    @Override
    public void execute(String to, String name, String password) {

        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            Context context = new Context();
            context.setVariable("nome", name);
            context.setVariable("email", to);
            context.setVariable("senha", password);
            context.setVariable("portalLink", "https://www.google.com.br/");

            String htmlContent = templateEngine.process("welcome-email", context);

            helper.setTo(to);
            helper.setSubject("Bem vindo ao Portal");
            helper.setText(htmlContent, true);
            helper.setFrom("routinelove36@gmail.com");

            mailSender.send(message);
        }catch(Exception e){
            throw new RuntimeException();
        }
        
    }
    
}
