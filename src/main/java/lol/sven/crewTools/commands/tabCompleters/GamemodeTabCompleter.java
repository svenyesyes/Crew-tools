package lol.sven.crewTools.commands.tabCompleters;

import lol.sven.crewTools.util.TabCompleteUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class GamemodeTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        ArrayList<String> gamemodes = new ArrayList<>();
        gamemodes.add("adventure");
        gamemodes.add("creative");
        gamemodes.add("spectator");
        gamemodes.add("survival");

        // Don't autocomplete when player has no permission to use this command.
        if (!sender.hasPermission("crewtools.gamemode")) {
            return new ArrayList<>();
        }

        if (args.length == 1) {

            if (label.equalsIgnoreCase("gamemode") || label.equalsIgnoreCase("gm")) {
                return TabCompleteUtil.filterStart(gamemodes, args[0]);
            }

            List<String> o = new ArrayList<>();
            for (OfflinePlayer p : Bukkit.getServer().getOnlinePlayers()) {
                o.add(p.getName());
            }
            return TabCompleteUtil.filterStart(o, args[0]);
        }

        if (args.length == 2) {
            List<String> o = new ArrayList<>();
            for (OfflinePlayer p : Bukkit.getServer().getOnlinePlayers()) {
                o.add(p.getName());
            }
            return TabCompleteUtil.filterStart(o, args[1]);
        }

        return new ArrayList<>();
    }
}
