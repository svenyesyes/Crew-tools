package lol.sven.crewTools.listeners;

import lol.sven.crewTools.menus.PlayerEnderchestMenu;
import lol.sven.crewTools.menus.PlayerInventoryMenu;
import lol.sven.crewTools.util.PredefinedMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class MenuListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if(e.getView().getTitle().equals(ChatColor.DARK_GRAY + "Preview Inventory")) {
            e.setCancelled(true);
            return;
        }

        if(e.getView().getTitle().equals(ChatColor.DARK_GRAY + "Preview Enderchest")) {
            e.setCancelled(true);
            return;
        }

        if(e.getView().getTitle().equals(ChatColor.DARK_GRAY + "Player Info")) {
            e.setCancelled(true);

            Player self = (Player) e.getWhoClicked();
            Player target = Bukkit.getPlayer(e.getView().getItem(0).getItemMeta().getItemName());

            if(target == null) {
                self.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.PLAYER_NOT_ONLINE);
            }

            if(e.getCurrentItem() == null) {
                return;
            }

            if(e.getCurrentItem().getType() == Material.OAK_DOOR) {
                self.sendMessage(PredefinedMessage.PREFIX + " " + target.getAddress().getHostString());
                self.closeInventory();
                return;
            }

            if(e.getCurrentItem().getType() == Material.CHEST) {
                self.closeInventory();
                new PlayerInventoryMenu(self, target, true);
                return;
            }

            if(e.getCurrentItem().getType() == Material.ENDER_CHEST) {
                self.closeInventory();
                new PlayerEnderchestMenu(self, target, true);
                return;
            }

            if(e.getCurrentItem().getType() == Material.ENDER_PEARL) {
                self.sendMessage(PredefinedMessage.PREFIX + " " + PredefinedMessage.TELEPORTED);
                self.teleport(target);
                self.closeInventory();
                return;
            }

            return;
        }


    }

}
