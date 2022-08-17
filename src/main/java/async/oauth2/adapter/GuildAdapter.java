package async.oauth2.adapter;

import com.freedom.accountauth.entity.embed.Guild;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class GuildAdapter implements JsonSerializer<Guild> {

    @Override
    public JsonElement serialize(Guild src, Type typeOfSrc, JsonSerializationContext context) {
        var json = new JsonObject();

        json.addProperty("id", src.getId());
        json.addProperty("name", src.getName());
        json.addProperty("icon", src.getIcon());
        json.addProperty("owner", src.isOwner());

        return json;
    }
}
