package async.oauth2;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Token {

    private String accessToken;
    private String refreshToken;

    private long expiresIn;
}
