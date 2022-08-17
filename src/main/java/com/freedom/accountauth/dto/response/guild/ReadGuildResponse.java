package com.freedom.accountauth.dto.response.guild;

import async.oauth2.DiscordAuthentication;
import async.oauth2.model.ChannelType;
import com.freedom.accountauth.entity.embed.Guild;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class ReadGuildResponse {

    private String id;

    private String name;
    private String icon;

    private boolean owner;

    private List<ReadChannelResponse> channels;

    private List<ReadRoleResponse> roles;

    @SneakyThrows
    public ReadGuildResponse(Guild guild) {
        this.id = guild.getId();
        this.name = guild.getName();
        this.icon = String.format("https://cdn.discordapp.com/icons/%s/%s.png", guild.getId(), guild.getIcon());
        this.owner = guild.isOwner();
    }

    @SneakyThrows
    public ReadGuildResponse(Guild guild, DiscordAuthentication authentication) {
        this.id = guild.getId();
        this.name = guild.getName();
        this.icon = String.format("https://cdn.discordapp.com/icons/%s/%s.png", guild.getId(), guild.getIcon());
        this.owner = guild.isOwner();

        if (authentication != null) {
            this.channels = authentication.getChannels(guild.getId()).get()
                    .stream()
                    .filter(channel -> channel.getType() == ChannelType.TEXT_CHANNEL)
                    .map(ReadChannelResponse::new)
                    .collect(Collectors.toList());


            this.roles = authentication.getRoles(guild.getId()).get()
                    .stream()
                    .map(ReadRoleResponse::new)
                    .collect(Collectors.toList());
        }
    }
}
