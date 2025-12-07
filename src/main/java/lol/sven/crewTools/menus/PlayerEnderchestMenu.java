package lol.sven.crewTools.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PlayerEnderchestMenu {

    public PlayerEnderchestMenu(Player self, Player target, Boolean preview) {

        if(!target.isOnline()) return;

        Inventory targetInv = target.getEnderChest();

        Inventory previewInv = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Preview Enderchest");
        for (int i = 0; i < 27; i++) {
            previewInv.setItem(i, targetInv.getItem(i));
        }

        self.openInventory(previewInv);

    }

}
