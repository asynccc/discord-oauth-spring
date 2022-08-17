package async.oauth2;

import async.oauth2.client.Client;
import async.oauth2.model.DiscordUser;
import async.oauth2.model.Channel;
import async.oauth2.model.Role;
import async.oauth2.util.Util;
import com.freedom.accountauth.dto.discord.AccessResponse;
import com.freedom.accountauth.entity.embed.Guild;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.google.gson.reflect.TypeToken;

@NoArgsConstructor
@Getter
public class DiscordAuthentication {

    private String clientId;
    private String clientSecret;
    private String redirectUri;

    private List<String> scopes;

    @Setter
    private String code;

    private Client client;

    public DiscordAuthentication(String clientId, String clientSecret, String redirectUri, String... scopes) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.scopes = List.of(scopes);

        this.client = new Client(this);
        client.build();
    }

    public CompletableFuture<DiscordUser> getUser(String token) {
        return client.get(Settings.USER_URI, token, DiscordUser.class);
    }

    public CompletableFuture<ArrayList<Guild>> getGuilds(String token) {
        var typeToken = new TypeToken<ArrayList<Guild>>() {};
        return client.get(Settings.GUILDS_URI, token, typeToken);
    }

    public CompletableFuture<List<Channel>> getChannels(String guildId) {
        var typeToken = new TypeToken<List<Channel>>() {};
        return client.get(String.format("https://discordapp.com/api/guilds/%s/channels", guildId), typeToken);
    }

    public CompletableFuture<List<Role>> getRoles(String guildId) {
        var typeToken = new TypeToken<List<Role>>() {};
        return client.get(String.format("https://discordapp.com/api/guilds/%s/roles", guildId), typeToken);
    }

    public CompletableFuture<DiscordUser> getUser(String guildId, String userId) {
        var typeToken = new TypeToken<DiscordUser>() {};
        return client.get(String.format("https://discordapp.com/api/guilds/%s/members/%s", guildId, userId), typeToken);
    }

    public AccessResponse getToken(RestOperations operations) {
        var requesters = Util.getTokenRequest(this);

        return client.post(operations, Settings.TOKEN_URI, requesters, AccessResponse.class);
    }

}
