package async.oauth2.adapter;

import async.oauth2.model.Role;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class RoleAdapter implements JsonDeserializer<Role> {

    @Override
    public Role deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final var object = json.getAsJsonObject();

        return new Role(
                object.get("id").getAsLong(),
                object.get("name").getAsString(),
                object.get("color").getAsInt()
        );
    }
}
