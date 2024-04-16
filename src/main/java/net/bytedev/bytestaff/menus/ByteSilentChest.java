package net.bytedev.bytestaff.menus;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.Array;
import java.util.Arrays;

public class ByteSilentChest implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {

        ItemStack[] TestItems = event.getInventory().getContents();

        TestItems[0] = new ItemStack(Material.DIAMOND_BLOCK);


        event.getInventory().setContents(TestItems);
    }
}