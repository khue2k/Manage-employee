package ptit.edu.checkin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MistakeDto {
    private String mistakeMorning;
    private String mistakeAfternoon;
    private LocalDate date;
}
