package net.bytedev.bytestaff.events;

import net.bytedev.bytestaff.ByteStaff;
import net.bytedev.bytestaff.commands.ByteStaffCommand;
import net.bytedev.bytestaff.menus.ByteInvseeMenu;
import net.bytedev.bytestaff.util.ByteMetaUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.Arrays;
import java.util.List;

public class ByteGUIEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player ClickPlayer = (Player) event.getWhoClicked();

        if (ClickPlayer.hasMetadata("ByteInvsee")) {

            if (!ClickPlayer.hasPermission("bytestaff.invsee.edit")) {

                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player ClosePlayer = (Player) event.getPlayer();

        if (ClosePlayer.hasMetadata("ByteInvsee")) {

            ClosePlayer.removeMetadata("ByteInvsee", ByteStaff.getInstance());
        }
    }
}

//Enable take, Close, Clear