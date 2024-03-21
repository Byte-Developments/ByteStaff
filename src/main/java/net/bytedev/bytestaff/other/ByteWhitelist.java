package net.bytedev.bytestaff.other;

import net.bytedev.bytestaff.files.ByteWhitelistDB;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ByteWhitelist {

    public static void ByteAddWH(Player ExecPlayer, String WHPlayerName) {

        if (Bukkit.getPlayer(WHPlayerName) != null && WHPlayerName != null && !WHPlayerName.isEmpty()) {

            Player WHPlayer = Bukkit.getPlayer(WHPlayerName);

            if (WHPlayer.hasPermission("bytestaff.whitelist.add")) {
                ByteWhitelistDB.ByteAddWH(WHPlayerName, WHPlayer.getUniqueId().toString(), true);

                ExecPlayer.sendMessage(Component.text("You've removed " + WHPlayerName + " from the whitelist.", NamedTextColor.RED));

                for (Player LoopWHPlayer : Bukkit.getOnlinePlayers()) {

                    if (!LoopWHPlayer.getName().equalsIgnoreCase(ExecPlayer.getName()) && LoopWHPlayer.hasPermission("bytestaff.whitelist.broadcast")) {
                        LoopWHPlayer.sendMessage(Component.text("Player " + WHPlayerName + " was removed from the whitelist.", NamedTextColor.RED));
                    }

                }
            }
            else {

                ExecPlayer.sendMessage(Component.text("Insufficient Permissions.", NamedTextColor.RED));
            }
        }
        else {
            ExecPlayer.sendMessage(Component.text("You've inputted something wrong.", NamedTextColor.RED));
        }
    }

    public static void ByteRemoveWH(Player ExecPlayer, String WHPlayerName) {

        if (Bukkit.getPlayer(WHPlayerName) != null && WHPlayerName != null && !WHPlayerName.isEmpty()) {

            Player WHPlayer = Bukkit.getPlayer(WHPlayerName);

            if (WHPlayer.hasPermission("bytestaff.whitelist.add")) {

                ByteWhitelistDB.ByteAddWH(WHPlayerName, WHPlayer.getUniqueId().toString(), true);

                ExecPlayer.sendMessage(Component.text("You've removed " + WHPlayerName + " from the whitelist.", NamedTextColor.RED));

                for (Player LoopWHPlayer : Bukkit.getOnlinePlayers()) {

                    if (!LoopWHPlayer.getName().equalsIgnoreCase(ExecPlayer.getName()) && LoopWHPlayer.hasPermission("bytestaff.whitelist.broadcast")) {
                        LoopWHPlayer.sendMessage(Component.text("Player " + WHPlayerName + " was removed from the whitelist.", NamedTextColor.RED));
                    }

                }
            }
            else {

                ExecPlayer.sendMessage(Component.text("Insufficient Permissions.", NamedTextColor.RED));
            }
        }
        else {
            ExecPlayer.sendMessage(Component.text("You've inputted something wrong.", NamedTextColor.RED));
        }
    }
}