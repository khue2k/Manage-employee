package ptit.edu.checkin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MistakeOfEmployeeDto {
    private LocalDate date;
    private String mistakeMorning;
    private String mistakeAfternoon;
}
