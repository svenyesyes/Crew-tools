package lol.sven.crewTools.commands;

import lol.sven.crewTools.util.PredefinedMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportAllCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("crewtools.teleport.all")) {
            sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.NO_PERMISSION);
            return true;
        }

        if(!(sender instanceof Player)) {
            sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.EXECUTE_AS_PLAYER);
            return true;
        }

        sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.TELEPORTING_OTHERS);
        Bukkit.getServer().getOnlinePlayers().forEach(player -> {
            if(player.getUniqueId() == ((Player) sender).getUniqueId()) return;
            player.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.TELEPORTED);
            player.teleport(((Player) sender));
        });

        return true;
    }

}
