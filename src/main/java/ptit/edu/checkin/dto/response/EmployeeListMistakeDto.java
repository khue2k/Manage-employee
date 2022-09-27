package ptit.edu.checkin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeListMistakeDto {
    private String fullName;
    private String position;
    private List<MistakeDto> timeChecks;
}
