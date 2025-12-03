package lol.sven.crewTools;

import lol.sven.crewTools.commands.PlayerInfoCommand;
import lol.sven.crewTools.commands.tabCompleters.PlayerInfoTabCompleter;
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
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
