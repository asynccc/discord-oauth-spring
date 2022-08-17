package com.freedom.accountauth.dto.request;

import com.freedom.accountauth.entity.embed.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class CreateUserRequest {

    private long id;

    private String username;
    private String discriminator;

    private String email;

    private Token token;
}
