package lol.sven.crewTools.util;

import lol.sven.crewTools.CrewTools;
import org.bukkit.ChatColor;

public enum PredefinedMessage {

    PREFIX("messages.prefix", "&8[&6CREW&8]"),
    SUCCESS("messages.success", "&a✔ &7Success!"),
    FAILED("messages.failed", "&c✘ &Failed!"),
    NO_PERMISSION("messages.no_permission", "&c✘ &7You are not allowed to do this!"),
    ERROR("messages.error", "&c✘ &7Something went wrong!"),
    EXECUTE_AS_PLAYER("messages.execute_as_player", "&c✘ &7You may only use this as a player!"),
    PLAYER_NOT_FOUND("messages.player_not_found", "&c✘ &7Could not find player!"),
    PLAYER_NOT_ONLINE("messages.player_not_online", "&c✘ &7This player is not online!"),
    OPEN_GUI("messages.open_gui", "&b🛈 &7Opening GUI."),
    TELEPORTED("messages.teleported", "&b🛈 &7Your are being teleported."),
    TELEPORTING_SELF("messages.teleported_self", "&a✔ &7Your are being teleported."),
    TELEPORTING_OTHERS("messages.teleported_others", "&a✔ &7Teleporting player(s).");

    private final String config_entry;
    private final String defaultString;

    PredefinedMessage(String config_entry, String defaultString) {
        this.config_entry = config_entry;
        this.defaultString = defaultString;
    }

    public String getConfigEntry() {
        return config_entry;
    }

    public String getDefaultString() {
        return defaultString;
    }

    /**
     * Get the raw string of this message set in the config or default if not present in config
     * @return The raw string of this message
     */
    public String toRawString() {
        String configMessage = CrewTools.getPlugin(CrewTools.class).getConfig().getString(config_entry);
        if (configMessage == null || configMessage.isEmpty()) {
            return defaultString;
        }
        return configMessage;
    }

    @Override
    public String toString() {
        String configMessage = CrewTools.getPlugin(CrewTools.class).getConfig().getString(config_entry);
        if (configMessage == null || configMessage.isEmpty()) {
            return ChatColor.translateAlternateColorCodes('&', defaultString) + ChatColor.RESET;
        }
        return ChatColor.translateAlternateColorCodes('&', configMessage) + ChatColor.RESET;
    }

}
