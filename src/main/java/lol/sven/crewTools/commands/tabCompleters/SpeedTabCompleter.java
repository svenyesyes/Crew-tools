package lol.sven.crewTools.commands.tabCompleters;

import lol.sven.crewTools.util.TabCompleteUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class SpeedTabCompleter implements TabCompleter {
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        // Don't autocomplete when player has no permission to use this command.
        if (!sender.hasPermission("crewtools.speed")) {
            return new ArrayList<>();
        }

        if (args.length == 1) {
            List<String> o = new ArrayList<>();
            o.add("reset");
            o.add("default");
            for (int i = 0; i < 10; i++) {
                o.add("" + (i + 1));
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
