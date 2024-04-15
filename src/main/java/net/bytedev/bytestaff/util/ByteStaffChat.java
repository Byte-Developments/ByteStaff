package net.bytedev.bytestaff.util;

import net.bytedev.bytestaff.files.ByteStaffChatDB;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ByteStaffChat {

    public static void ByteToggleSC(String NewSC, Player SCPlayer) {

        if (NewSC.equalsIgnoreCase("on") || NewSC.equalsIgnoreCase("true")) {

            if (SCPlayer.hasPermission("bytestaff.staffchat.toggle.on")) {
                ByteStaffChatDB.UpdateStaffChat(SCPlayer.getName(), SCPlayer.getUniqueId().toString(), true);

                SCPlayer.sendMessage(Component.text("You've toggled on StaffChat", NamedTextColor.GREEN));
            }
        }
        else if (NewSC.equalsIgnoreCase("off") || NewSC.equalsIgnoreCase("false")) {

            if (SCPlayer.hasPermission("bytestaff.staffchat.toggle.off")) {
                ByteStaffChatDB.UpdateStaffChat(SCPlayer.getName(), SCPlayer.getUniqueId().toString(), false);

                SCPlayer.sendMessage(Component.text("You've toggled off StaffChat", NamedTextColor.RED));
            }
        }
        else {

            SCPlayer.sendMessage(Component.text("Unknown option " + "'" + NewSC + "'", NamedTextColor.RED));

        }
    }

    public static void ByteToggleSC(String NewSC, Player SettingPlayer, String SCPlayerPar) {

        if (Bukkit.getPlayer(SCPlayerPar) != null) {

            Player SCPlayer = Bukkit.getPlayer(SCPlayerPar);

            if (NewSC.equalsIgnoreCase("on") || NewSC.equalsIgnoreCase("true")) {

                if (SettingPlayer.hasPermission("bytestaff.staffchat.others.on")) {
                    ByteStaffChatDB.UpdateStaffChat(SCPlayer.getName(), SCPlayer.getUniqueId().toString(), true);

                    SettingPlayer.sendMessage(Component.text("You've enabled StaffChat for " + SCPlayer.getName() + ".", NamedTextColor.GREEN));
                    SCPlayer.sendMessage(Component.text("StaffChat has been enabled by " + SettingPlayer.getName() + ".", NamedTextColor.GREEN));
                }
                else {
                    SettingPlayer.sendMessage(Component.text("You don't have permission to set other's StaffChat.", NamedTextColor.RED));
                }
            }
            else if (NewSC.equalsIgnoreCase("off") || NewSC.equalsIgnoreCase("false")) {

                if (SettingPlayer.hasPermission("bytestaff.staffchat.others.off")) {
                    ByteStaffChatDB.UpdateStaffChat(SCPlayer.getName(), SCPlayer.getUniqueId().toString(), false);

                    SettingPlayer.sendMessage(Component.text("You've disabled StaffChat for " + SCPlayer.getName() + ".", NamedTextColor.RED));
                    SCPlayer.sendMessage(Component.text("StaffChat has been disabled by " + SettingPlayer.getName() + ".", NamedTextColor.RED));
                }
                else {
                    SettingPlayer.sendMessage(Component.text("You don't have permission to set other's StaffChat.", NamedTextColor.RED));
                }
            }
            else {

                SCPlayer.sendMessage(Component.text("Unknown option " + "'" + NewSC + "'", NamedTextColor.RED));

            }
        }
        else {
            SettingPlayer.sendMessage(Component.text("That player doesn't exist.", NamedTextColor.RED));
        }

    }

}