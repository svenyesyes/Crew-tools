package lol.sven.crewTools.commands.tabCompleters;

import lol.sven.crewTools.util.TabCompleteUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TeleportTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        // Don't autocomplete when player has no permission to use this command.
        if (!sender.hasPermission("crewtools.teleport")) {
            return new ArrayList<>();
        }

        if (args.length == 1) {
            List<String> o = new ArrayList<>();
            for (OfflinePlayer p : Bukkit.getServer().getOnlinePlayers()) {
                o.add(p.getName());
            }
            return TabCompleteUtil.filterNames(o, args[0]);
        }

        if (args.length == 2) {
            if (!sender.hasPermission("crewtools.teleport.others")) {
                return new ArrayList<>();
            }
            List<String> o = new ArrayList<>();
            for (OfflinePlayer p : Bukkit.getServer().getOnlinePlayers()) {
                o.add(p.getName());
            }
            return TabCompleteUtil.filterNames(o, args[1]);
        }
        return new ArrayList<>();
    }
}
