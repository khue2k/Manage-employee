package ptit.edu.checkin.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpDto {
    private String userName;
    private String password;
}
