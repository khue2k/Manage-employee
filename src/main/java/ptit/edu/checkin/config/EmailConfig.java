package ptit.edu.checkin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.validation.constraints.Email;
import java.util.Properties;

@Configuration
@Email
public class EmailConfig {
    @Bean
    public JavaMailSender javaMailSenderBean() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(25);
        javaMailSender.setUsername("songkhecole01@gmail.com");
        javaMailSender.setPassword("rgzfmlwqbxfhjhha");
        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return javaMailSender;
    }
}
