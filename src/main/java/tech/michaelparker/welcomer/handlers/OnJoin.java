package tech.michaelparker.welcomer.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.configuration.file.FileConfiguration;
import tech.michaelparker.welcomer.Welcomer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
                //find and replace %player% with player name
                String message = config.getString("welcome-back-message");
                message = message.replace("%player%", player.getName());
                //get the player's last seen date
                long lastSeen = player.getLastPlayed();
                //convert the last seen date to a string
                String lastSeenString = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date(lastSeen));
                //find and replace %lastseen% with the last seen date
                message = message.replace("%lastseen%", lastSeenString);


                plugin.getServer().broadcastMessage(message);
            } else {
                plugin.getServer().broadcastMessage("Welcome back to the server!");
            }
        } else {
            if (config.contains("first-join-message")) {
                //find and replace %player% with player name
                String message = config.getString("first-join-message");
                message = message.replace("%player%", player.getName());
                plugin.getServer().broadcastMessage(message);


                // Give items to first-time players
                if (config.contains("first-join-items")) {
                    for (String itemString : config.getStringList("first-join-items")) {
                        String[] parts = itemString.split(" ");
                        if (parts.length == 2) {
                            Material material = Material.getMaterial(parts[0]);
                            int amount = Integer.parseInt(parts[1]);

                            if (material != null) {
                                player.getInventory().addItem(new ItemStack(material, amount));
                            }
                        }
                    }
                }
            } else {
                //global message to all players
                plugin.getServer().broadcastMessage("Welcome to the server!, Its your first time here!");

            }
        }
    }
}
