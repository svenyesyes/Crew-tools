package lol.sven.crewTools.commands;

import lol.sven.crewTools.util.PredefinedMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        boolean other = false;

        if (!sender.hasPermission("crewtools.teleport")) {
            sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.NO_PERMISSION);
            return true;
        }

        if (args.length == 0) {
            return false;
        }

        if (args.length >= 2) {
            other = true;
            if (!sender.hasPermission("crewtools.teleport.others")) {
                sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.NO_PERMISSION);
                return true;
            }
        }

        Player p = Bukkit.getPlayer(args[0]);
        if (p == null) {
            sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.PLAYER_NOT_ONLINE);
            return true;
        }

        Player p2;
        if (other) {
            p2 = Bukkit.getPlayer(args[1]);
            if (p2 == null) {
                sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.PLAYER_NOT_ONLINE);
                return true;
            }

            sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.TELEPORTING_OTHERS);
            p.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.TELEPORTED);
            p.teleport(p2);
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.EXECUTE_AS_PLAYER);
            return true;
        }

        sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.TELEPORTING_SELF);
        ((Player) sender).teleport(p);

        return true;
    }

}
