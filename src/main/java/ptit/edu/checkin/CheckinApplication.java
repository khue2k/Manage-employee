package ptit.edu.checkin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ptit.edu.checkin.repository.EmployeeRepository;

@Slf4j
@SpringBootApplication
public class CheckinApplication implements CommandLineRunner {

    public static void main(String[] args){
        SpringApplication.run(CheckinApplication.class, args);
    }

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {

    }
}
