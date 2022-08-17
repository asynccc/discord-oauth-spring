package async.oauth2.adapter;

import async.oauth2.model.Channel;
import async.oauth2.model.ChannelType;
import com.google.gson.*;

import java.lang.reflect.Type;

public class TextChannelAdapter implements JsonSerializer<Channel>, JsonDeserializer<Channel> {

    @Override
    public JsonElement serialize(Channel src, Type typeOfSrc, JsonSerializationContext context) {
        var json = new JsonObject();

        json.addProperty("id", src.getId());
        json.addProperty("type", src.getType().getId());
        json.addProperty("last_message_id", src.getLastMessageId());
        json.addProperty("name", src.getName());
        json.addProperty("position", src.getPosition());
        json.addProperty("guild_id", src.getGuildId());
        json.addProperty("nsfw", src.isNsfw());

        return json;
    }

    @Override
    public Channel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        var object = json.getAsJsonObject();
        var builder = Channel.builder();

        var id = Long.parseLong(object.get("id").getAsString());
        var type = ChannelType.fromId(object.get("type").getAsInt());

        if (object.has("last_message_id") && id == ChannelType.TEXT_CHANNEL.getId()) {
            var lastMessageId = object.get("last_message_id").getAsLong();
            builder.lastMessageId(lastMessageId);
        }

        if (object.has("nsfw") && id == ChannelType.TEXT_CHANNEL.getId()) {
            var nsfw = object.get("nsfw").getAsBoolean();
            builder.nsfw(nsfw);
        }

        var name = object.get("name").getAsString();
        var position = object.get("position").getAsInt();
        var guildId = object.get("guild_id").getAsLong();


        return builder.id(id).type(type).name(name).position(position).guildId(guildId).build();
    }
}
