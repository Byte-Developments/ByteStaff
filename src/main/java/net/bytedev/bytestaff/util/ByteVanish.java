package net.bytedev.bytestaff.util;

import net.bytedev.bytestaff.ByteStaff;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class ByteVanish {

    public static void ByteVanishMain(Player VanishPlayer) {

        if (VanishPlayer.isOp() || VanishPlayer.hasMetadata("bytestaff.vanish.use")) {

            if (VanishPlayer.hasMetadata("ByteVanish")) {

                VanishPlayer.removeMetadata("ByteVanish", ByteStaff.getInstance());
                VanishPlayer.sendMessage(Component.text("You've disabled vanish.", NamedTextColor.RED));

            }
            else {
                VanishPlayer.setMetadata("ByteVanish", new FixedMetadataValue(ByteStaff.getInstance(), true));

                for (Player LoopVanishPlayer : Bukkit.getOnlinePlayers()) {
                    if (!LoopVanishPlayer.isOp() || !LoopVanishPlayer.hasPermission("bytestaff.vanish.see")) {
                        LoopVanishPlayer.hidePlayer(VanishPlayer);
                    } else {
                        if (LoopVanishPlayer.isOp() || LoopVanishPlayer.hasPermission("bytestaff.vanish.broadcast")) {

                            LoopVanishPlayer.sendMessage(Component.text("Player " + VanishPlayer.getName() + " has vanished.", NamedTextColor.GREEN));
                        }
                    }
                }

            }

        }
        else {

            VanishPlayer.sendMessage(Component.text("No Permissions!", NamedTextColor.RED));
        }

    }

}