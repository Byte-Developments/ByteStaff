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
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ByteGUIEvent implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {

        Player OpenPlayer = (Player) event.getPlayer();

        if (OpenPlayer.hasMetadata("opened-invsee")) {
            System.out.println("Works");
            event.getView().setTitle(ByteStaffCommand.InvPlayer + "'s Inventory");
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        Player ClickPlayer = (Player) event.getWhoClicked();
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {

        Player ClosePlayer = (Player) event.getPlayer();

        if (ClosePlayer.hasMetadata("opened-invsee")) {
            ClosePlayer.removeMetadata("opened-invsee", ByteStaff.getInstance());
        }
    }
}

//Enable take, Close, Clear