package lol.sven.crewTools;

import lol.sven.crewTools.commands.PlayerInfoCommand;
import lol.sven.crewTools.commands.TeleportAllCommand;
import lol.sven.crewTools.commands.TeleportCommand;
import lol.sven.crewTools.commands.TeleportHereCommand;
import lol.sven.crewTools.commands.tabCompleters.NoArgsCompleter;
import lol.sven.crewTools.commands.tabCompleters.PlayerInfoTabCompleter;
import lol.sven.crewTools.commands.tabCompleters.TeleportHereTabCompleter;
import lol.sven.crewTools.commands.tabCompleters.TeleportTabCompleter;
import lol.sven.crewTools.listeners.MenuListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CrewTools extends JavaPlugin {

    @Override
    public void onEnable() {

        Logger logger = getLogger();

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        registerCommands();
        registerListeners();
    }

    private void registerCommands() {
        getCommand("playerinfo").setExecutor(new PlayerInfoCommand());
        getCommand("playerinfo").setTabCompleter(new PlayerInfoTabCompleter());
        getCommand("teleport").setExecutor(new TeleportCommand());
        getCommand("teleport").setTabCompleter(new TeleportTabCompleter());
        getCommand("teleporthere").setExecutor(new TeleportHereCommand());
        getCommand("teleporthere").setTabCompleter(new TeleportHereTabCompleter());
        getCommand("teleportall").setExecutor(new TeleportAllCommand());
        getCommand("teleportall").setTabCompleter(new NoArgsCompleter());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
