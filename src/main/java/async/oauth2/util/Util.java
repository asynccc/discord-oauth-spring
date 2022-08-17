package async.oauth2.util;

import async.oauth2.DiscordAuthentication;
import async.oauth2.adapter.GuildAdapter;
import async.oauth2.adapter.RoleAdapter;
import async.oauth2.adapter.TextChannelAdapter;
import async.oauth2.model.Channel;
import com.freedom.accountauth.entity.embed.Guild;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.SneakyThrows;

import org.springframework.util.LinkedMultiValueMap;

import java.lang.reflect.Modifier;

public class Util {

    @Getter
    public static Gson gson = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .registerTypeAdapter(Guild.class, new GuildAdapter())
            .registerTypeAdapter(Channel.class, new TextChannelAdapter())
            .registerTypeAdapter(RoleAdapter.class, new RoleAdapter())
            .create();

    public static LinkedMultiValueMap<String, String> getTokenRequest(DiscordAuthentication authentication) {
        var headers = new LinkedMultiValueMap<String, String>();
        headers.add("client_id", authentication.getClientId());
        headers.add("client_secret", authentication.getClientSecret());
        headers.add("grant_type", "authorization_code");
        headers.add("code", authentication.getCode());
        headers.add("redirect_uri", authentication.getRedirectUri());
        headers.add("scope", String.join(" ", authentication.getScopes()));

        return headers;
    }

    @SneakyThrows
    public static <T> T fromJson(String body, TypeToken<T> type) {
        return Util.getGson().fromJson(body, type.getType());
    }

}
