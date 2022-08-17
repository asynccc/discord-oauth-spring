package com.freedom.accountauth.entity;

import com.freedom.accountauth.entity.embed.Guild;
import com.freedom.accountauth.entity.embed.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    private long id;

    @Column
    private String username;

    @Column
    private String discriminator;

    @Column
    private String email;

    @Column
    private String avatar;

    @Column
    @Embedded
    private Token token;

    @Column
    @ElementCollection
    private List<Guild> guilds;

    public String getAvatarUrl() {
        return String.format("https://cdn.discordapp.com/avatars/%s/%s.png?size=96", this.id, this.avatar);
    }

    public static User fromAttributes(Map<String, Object> attributes) {
        return User.builder()
                .id(Long.parseLong((String) attributes.get("id")))
                .username((String) attributes.get("username"))
                .discriminator((String) attributes.get("discriminator"))
                .avatar((String) attributes.get("avatar"))
                .email((String) attributes.get("email"))
                .build();
    }

    public void updateGuilds(List<Guild> guilds) {
        this.guilds = guilds;
    }
}
