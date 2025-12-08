package lol.sven.crewTools.commands;

import lol.sven.crewTools.util.PredefinedMessage;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        GameMode gm;

        // gmc, gamemodec, etc.
        if (args.length == 0) {
            gm = getGamemodeFromLabel(label);
            if (gm == null) {
                return false;
            }
            if (!hasGamemodePermission(sender, gm)) {
                sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.NO_PERMISSION);
                return true;
            }
            sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.UPDATED_GAMEMODE);
            ((Player) sender).setGameMode(gm);
            return true;
        }

        // gmc etc <player> OR gamemode <mode>
        if (args.length == 1) {

            // gamemode <mode>
            if (label.equalsIgnoreCase("gamemode") || label.equalsIgnoreCase("gm")) {
                gm = getGamemodeFromArg(args[0]);
                if (gm == null) {
                    return false;
                }
                if (!hasGamemodePermission(sender, gm)) {
                    sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.NO_PERMISSION);
                    return true;
                }
                sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.UPDATED_GAMEMODE);
                ((Player) sender).setGameMode(gm);
                return true;
            }

            // gmc etc <player>
            Player p = Bukkit.getPlayer(args[0]);
            if (p == null) {
                sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.PLAYER_NOT_ONLINE);
                return true;
            }

            gm = getGamemodeFromLabel(label);
            if (gm == null) {
                return false;
            }

            if (!hasGamemodePermission(sender, gm)) {
                sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.NO_PERMISSION);
            }

            sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.UPDATED_GAMEMODE_OTHER);
            p.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.UPDATED_GAMEMODE);
            p.setGameMode(gm);
            return true;
        }

        // gamemode <mode> <player>
        if (args.length == 2) {

            gm = getGamemodeFromArg(args[0]);

            if (gm == null) {
                return false;
            }

            Player p = Bukkit.getPlayer(args[1]);
            if (p == null) {
                sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.PLAYER_NOT_ONLINE);
                return true;
            }

            if (!hasGamemodePermission(sender, gm)) {
                sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.NO_PERMISSION);
            }

            sender.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.UPDATED_GAMEMODE_OTHER);
            p.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.UPDATED_GAMEMODE);
            p.setGameMode(gm);
            return true;
        }


        return false;
    }

    private GameMode getGamemodeFromLabel(String label) {

        if (label.equalsIgnoreCase("gms") || label.equalsIgnoreCase("gamemodes")) {
            return GameMode.SURVIVAL;
        }
        if (label.equalsIgnoreCase("gmc") || label.equalsIgnoreCase("gamemodec")) {
            return GameMode.CREATIVE;
        }
        if (label.equalsIgnoreCase("gmsp") || label.equalsIgnoreCase("gamemodesp")) {
            return GameMode.SPECTATOR;
        }
        if (label.equalsIgnoreCase("gma") || label.equalsIgnoreCase("gamemodea")) {
            return GameMode.ADVENTURE;
        }

        return null;
    }

    private GameMode getGamemodeFromArg(String arg) {
        if (arg.equalsIgnoreCase("0") || arg.equalsIgnoreCase("s") || arg.equalsIgnoreCase("survival") || arg.contains("surv")) {
            return GameMode.SURVIVAL;
        }
        if (arg.equalsIgnoreCase("1") || arg.equalsIgnoreCase("c") || arg.equalsIgnoreCase("creative") || arg.contains("crea")) {
            return GameMode.CREATIVE;
        }
        if (arg.equalsIgnoreCase("2") || arg.equalsIgnoreCase("a") || arg.equalsIgnoreCase("adventure") || arg.contains("adv")) {
            return GameMode.ADVENTURE;
        }
        if (arg.equalsIgnoreCase("3") || arg.equalsIgnoreCase("sp") || arg.equalsIgnoreCase("spectator") || arg.contains("spec")) {
            return GameMode.SPECTATOR;
        }

        return null;
    }

    private boolean hasGamemodePermission(CommandSender sender, GameMode gamemode) {
        String permissionString;
        switch (gamemode) {
            case CREATIVE:
                permissionString = "crewtools.gamemode.creative";
                break;
            case SURVIVAL:
                permissionString = "crewtools.gamemode.survival";
                break;
            case ADVENTURE:
                permissionString = "crewtools.gamemode.adventure";
                break;
            case SPECTATOR:
                permissionString = "crewtools.gamemode.spectator";
                break;
            default:
                return false;
        }

        return sender.hasPermission(permissionString);
    }

}
