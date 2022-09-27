package ptit.edu.checkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import ptit.edu.checkin.entity.Employee;

@Component
@EnableAsync
public class MailService {
    @Autowired
    JavaMailSender mailSender;
    @Async
    public  void  sendEmail(Employee employee){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(employee.getEmail());
        mailMessage.setSubject("Welcome to PTIT HN");
        mailMessage.setText("Your number check-in is: "+employee.getNumberCheck());
        mailSender.send(mailMessage);
    }
}
