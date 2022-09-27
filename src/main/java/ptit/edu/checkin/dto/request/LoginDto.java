package ptit.edu.checkin.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginDto {
    private String userName;
    private String password;
}
