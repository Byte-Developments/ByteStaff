package net.bytedev.bytestaff.menus;

import net.bytedev.bytestaff.ByteStaff;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;


public class ByteInvseeMenu {

    public static void ByteOpenInvsee(Player viewer, String target) {

        Player TargetPlayer = Bukkit.getPlayer(target);

        if (TargetPlayer != null) {

            viewer.openInventory(TargetPlayer.getInventory());
        }
    }

}