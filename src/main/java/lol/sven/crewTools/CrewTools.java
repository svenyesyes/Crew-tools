package lol.sven.crewTools;

import lol.sven.crewTools.commands.PlayerInfoCommand;
import lol.sven.crewTools.commands.tabCompleters.PlayerInfoTabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public final class CrewTools extends JavaPlugin {

    @Override
    public void onEnable() {

        Logger logger = getLogger();

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        registerCommands();
    }

    private void registerCommands() {
        getCommand("playerinfo").setExecutor(new PlayerInfoCommand());
        getCommand("playerinfo").setTabCompleter(new PlayerInfoTabCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
