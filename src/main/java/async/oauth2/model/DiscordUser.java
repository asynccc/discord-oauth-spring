package async.oauth2.model;

import com.freedom.accountauth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Locale;

@AllArgsConstructor
@Data
public class DiscordUser {

    private String id;
    private String username;
    private String discriminator;
    private String avatar;

    private Locale locale;

    private String getAvatarUrl(int size) {
        return String.format("https://cdn.discordapp.com/avatars/%s/%s.png?size=%s", id, avatar, size);
    }

    public User toUser() {
        return User.builder()
                .id(Long.parseLong(this.id))
                .username(this.username)
                .discriminator(this.discriminator)
                .avatar(this.avatar)
                .build();
    }
}
