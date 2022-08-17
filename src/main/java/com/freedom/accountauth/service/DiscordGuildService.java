package com.freedom.accountauth.service;

import async.oauth2.DiscordAuthentication;
import com.freedom.accountauth.dto.response.guild.ReadGuildResponse;
import com.freedom.accountauth.dto.response.user.ReadUserResponse;
import com.freedom.accountauth.exception.GuildNotFoundException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DiscordGuildService {

    private final DiscordAuthentication authentication;

    @SneakyThrows
    public ReadGuildResponse findById(String id) {
        return authentication.getGuilds(id)
                .get().stream()
                .filter(guild -> guild.getId().equals(id))
                .map(guild -> new ReadGuildResponse(guild, authentication))
                .findFirst()
                .orElseThrow(GuildNotFoundException::new);
    }

}
