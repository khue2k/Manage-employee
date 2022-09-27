package ptit.edu.checkin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TimeCheckDto {
    private LocalTime timeCheckIn;
    private LocalTime timeCheckOut;
    private LocalDate date;
}
