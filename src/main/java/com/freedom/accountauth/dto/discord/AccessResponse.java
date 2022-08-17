package com.freedom.accountauth.dto.discord;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccessResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("scope")
    private String scope;

    public OAuth2AccessToken.TokenType getTokenType() {
        return OAuth2AccessToken.TokenType.BEARER.getValue().equalsIgnoreCase(tokenType) ? OAuth2AccessToken.TokenType.BEARER : null;
    }

    public Set<String> getScopes() {
        return scope.isEmpty() ? Collections.emptySet() : Stream.of(scope.split("\\s+")).collect(Collectors.toSet());
    }
}
