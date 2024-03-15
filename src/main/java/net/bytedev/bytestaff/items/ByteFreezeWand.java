package net.bytedev.bytestaff.items;

import net.bytedev.bytestaff.util.ByteMetaUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ByteFreezeWand {

    public static void GiveFreezeWand(Player WandPlayer) {

        if (WandPlayer.hasPermission("bytestaff.freeze.wand")) {

            ItemStack WandItem = ByteItemBuilder.BuildItem("<#fc9b56><bold>FREEZE WAND</bold></#fc9b56>", "BLAZE_ROD", Arrays.asList("<dark_gray><underline>test</underline></dark_gray>", "<gray>This is test item.</gray>"), Arrays.asList(""), Arrays.asList(""));

            WandItem.setItemMeta(ByteMetaUtil.ByteAddMeta(WandItem.getItemMeta(), "bytestaff-freeze-item"));

            WandPlayer.getInventory().addItem(WandItem);
        }
    }
}