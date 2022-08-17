package com.freedom.accountauth.security;

import com.freedom.accountauth.client.RestOAuth2AccessTokenResponseClient;
import com.freedom.accountauth.controller.service.RestOAuth2UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .oauth2Login()
                .tokenEndpoint().accessTokenResponseClient(new RestOAuth2AccessTokenResponseClient(restOperations()))
                .and()
                .userInfoEndpoint().userService(new RestOAuth2UserService(restOperations()));
    }

    @Bean
    public RestOperations restOperations() {
        return new RestTemplate();
    }

}
