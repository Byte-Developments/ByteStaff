package net.bytedev.bytestaff.menus;

import net.bytedev.bytestaff.ByteStaff;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.metadata.FixedMetadataValue;


public class ByteInvseeMenu {

    public static void ByteOpenInvsee(Player OpenPlayer, String InvseePlayer) {

        if (Bukkit.getServer().getPlayer(InvseePlayer) != null) {

            Inventory InvseeInv = Bukkit.getPlayer(InvseePlayer).getInventory();

            OpenPlayer.setMetadata("opened-invsee", new FixedMetadataValue(ByteStaff.getInstance(), true));
            OpenPlayer.openInventory(InvseeInv);

        }
        else {
            OpenPlayer.sendMessage(Component.text("This player isn't currently online.", NamedTextColor.RED));
        }

    }

}