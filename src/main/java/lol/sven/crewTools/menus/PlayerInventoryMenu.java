package lol.sven.crewTools.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PlayerInventoryMenu {

    public PlayerInventoryMenu(Player self, Player target, Boolean preview) {

        if(!target.isOnline()) return;

        Inventory targetInv = target.getInventory();

        Inventory previewInv = Bukkit.createInventory(null, 45, ChatColor.DARK_GRAY + "Preview Inventory");
        for (int i = 0; i < 9; i++) {
            previewInv.setItem(i + 27, targetInv.getItem(i));
        }
        for (int i = 9; i < 36; i++) {
            previewInv.setItem(i - 9, targetInv.getItem(i));
        }
        for (int i = 36; i < 41; i++) {
            previewInv.setItem(i, targetInv.getItem(i));
        }

        self.openInventory(previewInv);

    }

}
