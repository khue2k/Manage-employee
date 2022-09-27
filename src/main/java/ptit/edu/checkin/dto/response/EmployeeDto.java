package ptit.edu.checkin.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeDto {

    Long getId();

    String getFullName();

    int getAge();

    String getPosition();

    String getEmail();

    int getNumberCheck();

    @Value("#{ 'name: ' + target.fullName +' position:'+ target.position}")
    String getDetail();
}