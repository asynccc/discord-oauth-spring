package async.oauth2.adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.HashMap;

public class TokenRequestAdapter implements JsonSerializer<HashMap<String, String>> {

    @Override
    public JsonElement serialize(HashMap<String, String> src, Type typeOfSrc, JsonSerializationContext context) {
        var json = new JsonObject();

        json.addProperty("client_id", src.get("client_id"));
        json.addProperty("client_secret", src.get("client_secret"));
        json.addProperty("grant_type", "authorization_code");
        json.addProperty("code", src.get("code"));
        json.addProperty("redirect_uri", src.get("redirect_uri"));
        json.addProperty("scope", src.get("scope"));

        return json;
    }
}
