package net.bytedev.bytestaff.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ByteVanish {

    public static void ByteVanishMain(Player VanishPlayer) {
        for (Player LoopVanishPlayer : Bukkit.getOnlinePlayers()) {
            if (!LoopVanishPlayer.isOp() || !LoopVanishPlayer.hasPermission("bytestaff.vanish.see")) {
                LoopVanishPlayer.hidePlayer(VanishPlayer);
            }
            else {
                if (LoopVanishPlayer.isOp() || LoopVanishPlayer.hasPermission("bytestaff.vanish.broadcast")) {
                    LoopVanishPlayer.sendMessage(Component.text("Player " + VanishPlayer.getName() + " has vanished.", NamedTextColor.GREEN));
                }
            }
        }
    }
}
