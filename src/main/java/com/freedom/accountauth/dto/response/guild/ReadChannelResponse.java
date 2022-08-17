package com.freedom.accountauth.dto.response.guild;

import async.oauth2.model.Channel;
import async.oauth2.model.ChannelType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReadChannelResponse {

    private long id;
    private String name;
    private ChannelType type;
    public ReadChannelResponse(Channel channel) {
        this.id = channel.getId();
        this.name = channel.getName();
        this.type = channel.getType();
    }
}
