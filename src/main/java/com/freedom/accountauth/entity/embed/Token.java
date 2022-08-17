package com.freedom.accountauth.entity.embed;

import lombok.*;

import javax.persistence.Embeddable;
import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class Token {

    private String accessToken;
    private String refreshToken;

    private Instant expiresIn;

}
