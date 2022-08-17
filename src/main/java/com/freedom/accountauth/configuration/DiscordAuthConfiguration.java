package com.freedom.accountauth.configuration;

import async.oauth2.DiscordAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordAuthConfiguration {

    @Value("${spring.security.oauth2.client.registration.discord.client-id}")
    private String CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.discord.client-secret}")
    private String CLIENT_SECRET;

    @Value("${spring.security.oauth2.client.registration.discord.redirect-uri}")
    private String REDIRECT_URI;

    @Bean
    public DiscordAuthentication discordAuthentication() {
        return new DiscordAuthentication(CLIENT_ID, CLIENT_SECRET, REDIRECT_URI.replace("{baseUrl}", "http://localhost:8080"), "identify", "email", "guilds");
    }

}
