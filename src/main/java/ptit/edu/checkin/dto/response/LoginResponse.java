package ptit.edu.checkin.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {
    public String accessToken;
    private String tokenType="Bearer";

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
