package net.bytedev.bytestaff.util;

import net.bytedev.bytestaff.ByteStaff;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class ByteFreeze {

    public static void ByteFreezeMain(Player FreezePlayer) {

        FreezePlayer.setMetadata("FrozenPlayer", new FixedMetadataValue(ByteStaff.getInstance(), true));

        for (Player LoopFreezePlayer : Bukkit.getOnlinePlayers()) {

            if (LoopFreezePlayer.isOp() || LoopFreezePlayer.hasPermission("bytestaff.freeze.broadcast")) {

                if (LoopFreezePlayer.hasPermission("bytestaff.freeze.tools")) {

                    LoopFreezePlayer.sendMessage(Component
                            .text("Player " + FreezePlayer.getName() + " has now been frozen! ")
                            .append(Component.text("[BAN] ", NamedTextColor.RED).decorate(TextDecoration.BOLD))
                            .append(Component.text("[TP] ", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.BOLD))
                            .append(Component.text("[UN-FREEZE]", NamedTextColor.AQUA).decorate(TextDecoration.BOLD))
                    );
                }
                else {

                    LoopFreezePlayer.sendMessage(Component.text("Player " + FreezePlayer.getName() + " has now been frozen!"));
                }
            }

        }
    }
}