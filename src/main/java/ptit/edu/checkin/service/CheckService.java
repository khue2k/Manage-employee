package ptit.edu.checkin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ptit.edu.checkin.dto.response.ResponseDto;
import ptit.edu.checkin.entity.Employee;
import ptit.edu.checkin.entity.TimeCheck;
import ptit.edu.checkin.repository.EmployeeRepository;
import ptit.edu.checkin.repository.TimeCheckRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Slf4j
@Service
public class CheckService {
    @Autowired
    TimeCheckRepository timeCheckRepository;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseDto checkIn(int numberCheck) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee employee = employeeRepository.findByNumberCheckAndFullName(numberCheck, userName);
        if (employee != null) {
            LocalDate nowDate = LocalDate.now();
            if (!timeCheckRepository.existsByDate(nowDate)) {
                LocalTime nowTime = LocalTime.now();
                LocalTime timeLimited = LocalTime.parse("08:30:00.00000");

                TimeCheck timeCheck = new TimeCheck();
                if (nowTime.isBefore(timeLimited)) {
                    timeCheck.setMistakeMorning("early");
                } else {
                    timeCheck.setMistakeMorning("late");
                }
                timeCheck.setNumberCheck(numberCheck);
                timeCheck.setDate(nowDate);
                timeCheck.setTimeCheckIn(nowTime);
                timeCheck.setEmployee(employee);
                timeCheckRepository.save(timeCheck);
                return new ResponseDto("check in success", "ok");
            } else {
                return new ResponseDto("you have already check-in", "failed");
            }
        } else
            return new ResponseDto("the check number not found or the check number does not belong to you! ", "failed");
    }

    public ResponseDto checkOut(int numberCheck) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info(userName);
        Employee employee = employeeRepository.findByNumberCheckAndFullName(numberCheck, userName);
        if (employee != null) {
            LocalDate nowDate = LocalDate.now();
            if (timeCheckRepository.existsByDate(nowDate)) {
                LocalTime nowTime = LocalTime.now();
                LocalTime timeLimited = LocalTime.parse("17:30:00.00000");
                TimeCheck timeCheck = timeCheckRepository.findByNumberCheckAndDate(numberCheck, nowDate).get(0);
                if (!nowTime.isAfter(timeLimited)) {
                    timeCheck.setMistakeAfternoon("late");
                } else {
                    timeCheck.setMistakeAfternoon("early");
                }
                timeCheck.setTimeCheckOut(nowTime);
                timeCheck.setEmployee(employee);
                timeCheckRepository.save(timeCheck);
                return new ResponseDto("check out success", "ok");
            }
            return new ResponseDto("you have not check-in","failed");
        }
            return new ResponseDto("the check number not found or the check number does not belong to you!", "failed");
    }

    @Scheduled(cron = "0 00 22 ? * MON-FRI")
    public void  daily() {
        LocalDate nowDate = LocalDate.now();
        List<Employee> employees = employeeRepository.findAll();
        employees.forEach(employee -> {
            int numberCheck = employee.getNumberCheck();
            String email = employee.getEmail();
            TimeCheck timeCheck = timeCheckRepository.findByDateAndNumberCheck(nowDate, numberCheck);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject("From PTIT HN !");
            mailMessage.setText(timeCheck.getMistakeMorning() + "and time checkin is:  " + timeCheck.getTimeCheckIn()
                    + ". /n Time checkout is: " + timeCheck.getTimeCheckOut() + " " + timeCheck.getMistakeAfternoon());
            mailSender.send(mailMessage);
        });
    }
}
