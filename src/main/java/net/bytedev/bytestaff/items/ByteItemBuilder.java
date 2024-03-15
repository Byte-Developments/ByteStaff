package net.bytedev.bytestaff.items;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.optional.qual.OptionalBottom;

import java.util.ArrayList;
import java.util.List;

public class ByteItemBuilder {

    public static ItemStack BuildItem(String DisplayName, String MaterialType, List<String> OldLore, List<String> EnchantList, List<String> PropertiesList) {

        ItemStack FinalItem = new ItemStack(Material.BARRIER);

        List<Component> FinalLore = new ArrayList<>();

        ItemMeta FinalMeta = FinalItem.getItemMeta();

        if (MaterialType != null && !MaterialType.isEmpty()) {

            Material CustomMaterial = Material.getMaterial(MaterialType);

            if (CustomMaterial != null && !CustomMaterial.isEmpty()) {
                FinalItem.setType(CustomMaterial);
            }
        }

        if (DisplayName != null && !DisplayName.isEmpty()) {

            Component ItemName = MiniMessage.miniMessage().deserialize(DisplayName);

            FinalMeta.displayName(ItemName);
        }

        if (OldLore != null && !OldLore.isEmpty()) {

            for (String LoopLore : OldLore) {

                Component LoopLoreLine = MiniMessage.miniMessage().deserialize(LoopLore);

                FinalLore.add(LoopLoreLine);
            }
        }

        if (EnchantList != null && !EnchantList.isEmpty()) {

            for (String LoopEnchFull : EnchantList) {

                if (!LoopEnchFull.isEmpty() && LoopEnchFull.contains(";")) {
                    String EnchName = LoopEnchFull.split(";")[0].replace(";", "");
                    int EnchLevel = Integer.parseInt(LoopEnchFull.split(";")[1].replace(";", ""));

                    Enchantment Ench = Enchantment.getByName(EnchName);

                    if (Ench != null) {
                        FinalMeta.addEnchant(Ench, EnchLevel, true);
                    }
                }
            }
        }

        return FinalItem;
    }

}