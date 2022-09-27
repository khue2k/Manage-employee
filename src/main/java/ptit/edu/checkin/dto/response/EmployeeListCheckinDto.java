package ptit.edu.checkin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeListCheckinDto {
    private String fullName;
    private String position;
    private List<TimeCheckDto> timeChecks;
}
