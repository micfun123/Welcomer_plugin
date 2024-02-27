package tech.michaelparker.welcomer.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.configuration.file.FileConfiguration;
import tech.michaelparker.welcomer.Welcomer;
import tech.michaelparker.welcomer.utils.Formatter;
import net.md_5.bungee.api.chat.BaseComponent;

public class OnJoin implements Listener {

    private final Welcomer plugin;

    public OnJoin(Welcomer plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Get default config file and get the first join message
        FileConfiguration config = plugin.getConfig();

        if (player.hasPlayedBefore()) {
            // Get welcome back message from config file
            if (config.contains("welcome-back-message")) {
                // find and replace %player% with player name
                String message = config.getString("welcome-back-message");

                BaseComponent[] text = Formatter.builder()
                        .template(message)
                        .lastSeen(player.getLastPlayed())
                        .playerName(player.getName())
                        .build()
                        .format();

                player.spigot().sendMessage(text);
            }
        } else {
            if (config.contains("first-join-message")) {
                String message = config.getString("first-join-message");

                BaseComponent[] text = Formatter.builder()
                        .template(message)
                        .playerName(player.getName())
                        .build()
                        .format();

                player.spigot().sendMessage(text);
            }
        }
    }
}
