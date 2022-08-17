package com.freedom.accountauth.dto.response.user;

import async.oauth2.DiscordAuthentication;
import com.freedom.accountauth.dto.response.guild.ReadGuildResponse;
import com.freedom.accountauth.entity.User;
import com.freedom.accountauth.entity.embed.Guild;
import com.freedom.accountauth.entity.embed.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class ReadUserResponse {

    private long id;

    private String username;
    private String discriminator;

    private String email;

    private String avatar;

    private Token token;

    private List<ReadGuildResponse> guilds;

    public ReadUserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.discriminator = user.getDiscriminator();
        this.avatar = user.getAvatarUrl();
        this.email = user.getEmail();
        this.token = user.getToken();
        this.guilds = user.getGuilds().stream()
                .map(ReadGuildResponse::new)
                .collect(Collectors.toList());
    }
}
