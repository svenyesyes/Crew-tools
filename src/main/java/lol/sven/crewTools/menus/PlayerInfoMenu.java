package lol.sven.crewTools.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.Objects;

public class PlayerInfoMenu {

    public PlayerInfoMenu(Player self, Player target) {

        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_GRAY + "Player Info");

        ItemStack playerSkull = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta playerSkullMeta = (SkullMeta) playerSkull.getItemMeta();
        playerSkullMeta.setDisplayName(ChatColor.YELLOW + target.getName());
        playerSkullMeta.setLore(List.of(ChatColor.GRAY + target.getUniqueId().toString()));
        playerSkullMeta.setOwnerProfile(target.getPlayerProfile());
        playerSkull.setItemMeta(playerSkullMeta);
        inv.setItem(0, playerSkull);

        ItemStack IpAddress = new ItemStack(Material.OAK_DOOR, 1);
        ItemMeta IpAddressMeta = IpAddress.getItemMeta();
        IpAddressMeta.setDisplayName(ChatColor.YELLOW + "IP Address");
        IpAddressMeta.setLore(List.of(ChatColor.RED + "Click to put IP in chat"));
        IpAddress.setItemMeta(IpAddressMeta);
        inv.setItem(2, IpAddress);

        ItemStack ping = new ItemStack(Material.BEACON, 1);
        ItemMeta pingMeta = ping.getItemMeta();
        pingMeta.setDisplayName(ChatColor.YELLOW + "Ping");
        pingMeta.setLore(List.of(ChatColor.GRAY + "" + target.getPing() + "ms"));
        ping.setItemMeta(pingMeta);
        inv.setItem(3, ping);

        ItemStack teleport = new ItemStack(Material.ENDER_PEARL, 1);
        ItemMeta teleportMeta = teleport.getItemMeta();
        teleportMeta.setDisplayName(ChatColor.YELLOW + "Teleport");
        teleport.setItemMeta(teleportMeta);
        inv.setItem(8, teleport);


        self.openInventory(inv);


    }

}
