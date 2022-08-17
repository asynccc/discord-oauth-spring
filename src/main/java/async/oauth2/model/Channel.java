package async.oauth2.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class Channel {

    private long id;
    private long lastMessageId;

    private ChannelType type;

    private String name;
    private int position;
    private long guildId;

    private boolean nsfw;

}
