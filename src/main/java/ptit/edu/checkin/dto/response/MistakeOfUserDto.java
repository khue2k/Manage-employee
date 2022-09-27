package ptit.edu.checkin.dto.response;

import java.time.LocalDate;

public interface MistakeOfUserDto {
    String getMistakeMorning();
    String getMistakeAfternoon();
    LocalDate getDate();
}
