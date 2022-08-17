package async.oauth2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChannelType {

    TEXT_CHANNEL(0),
    VOICE_CHANNEL(2),
    CATEGORY(4);

    private final int id;

    public static ChannelType fromId(int id) {
        for (ChannelType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }
}
