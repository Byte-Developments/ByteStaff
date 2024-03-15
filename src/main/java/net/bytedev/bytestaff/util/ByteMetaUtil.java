package net.bytedev.bytestaff.util;

import net.bytedev.bytestaff.ByteStaff;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ByteMetaUtil {

    public static ItemMeta ByteAddMeta(ItemMeta DataTagItem, String DataTagName) {
        NamespacedKey DataTagKey = new NamespacedKey(ByteStaff.getInstance(), DataTagName);

        DataTagItem.getPersistentDataContainer().set(DataTagKey, PersistentDataType.INTEGER, 1);

        return DataTagItem;
    }

    public static boolean ByteGetMeta(ItemMeta GetDataItem, String GetDataName) {
        NamespacedKey GetDataKey = new NamespacedKey(ByteStaff.getInstance(), GetDataName);
        PersistentDataContainer GetDataContainer = GetDataItem.getPersistentDataContainer();

        return GetDataContainer.has(GetDataKey, PersistentDataType.INTEGER);
    }
}