package lol.sven.crewTools.commands;

import lol.sven.crewTools.util.PredefinedMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("crewtools.speed")) {
            sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.NO_PERMISSION);
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.EXECUTE_AS_PLAYER);
            return true;
        }

        float value = 0;
        boolean isDefault = false;

        if (args.length == 0) {
            return false;
        }

        if (args[0].equalsIgnoreCase("default") || args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("r")) {
            isDefault = true;
        } else {

            try {
                value = Float.parseFloat(args[0]);
            } catch (NumberFormatException e) {
                return false;
            }

            if (value < 0 || value > 10) {
                return false;
            }

        }


        if (args.length == 1) {

            Player p = (Player) sender;

            if ((p.isFlying() && !label.equalsIgnoreCase("walkspeed")) || label.equalsIgnoreCase("flyspeed")) {

                if (isDefault) value = 1;
                p.setFlySpeed(value * 0.1f);
            } else {

                if (isDefault) value = 2;
                p.setWalkSpeed(value * 0.1f);
            }
            return true;

        } else if (args.length == 2) {
            Player p = Bukkit.getPlayer(args[1]);

            if (p == null) {
                sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.PLAYER_NOT_ONLINE);
                return true;
            }

            if ((p.isFlying() && !label.equalsIgnoreCase("walkspeed")) || label.equalsIgnoreCase("flyspeed")) {

                if (isDefault) value = 1;
                p.setFlySpeed(value * 0.1f);
            } else {

                if (isDefault) value = 2;
                p.setWalkSpeed(value * 0.1f);
            }
            return true;

        }

        return false;
    }
}
