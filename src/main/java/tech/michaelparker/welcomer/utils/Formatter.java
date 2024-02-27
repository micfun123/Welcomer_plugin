package tech.michaelparker.welcomer.utils;

import de.themoep.minedown.MineDown;
import lombok.Builder;
import net.md_5.bungee.api.chat.BaseComponent;

@Builder
public class Formatter {

    String template;

    String playerName;
    long lastSeen;

    public BaseComponent[] format() {
        return new MineDown(template)
                .replace("player", playerName)
                .replace("lastseen", formatLastSeen())
                .toComponent();

    }

    private String formatLastSeen() {
        if (lastSeen == 0) {
            return "";
        }

        return new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
                .format(new java.util.Date(lastSeen));
    }
}
