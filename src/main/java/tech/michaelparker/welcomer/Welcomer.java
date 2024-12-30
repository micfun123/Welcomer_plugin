package tech.michaelparker.welcomer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import tech.michaelparker.welcomer.handlers.OnJoin;

public final class Welcomer extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getLogger().info("\n" + "\n" + "\n" + "WELCOME ALL TO THE WELCOME PLUGIN" +"\n"+ "https://buymeacoffee.com/michaelrbparker" +  "\n" + "\n" + "\n");
        saveDefaultConfig();

        // Register the event listener
        getServer().getPluginManager().registerEvents(new OnJoin(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
