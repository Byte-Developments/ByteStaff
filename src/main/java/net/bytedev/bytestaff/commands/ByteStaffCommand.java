package net.bytedev.bytestaff.commands;

import net.bytedev.bytestaff.ByteStaff;
import net.bytedev.bytestaff.items.ByteFreezeWand;
import net.bytedev.bytestaff.menus.ByteInvseeMenu;
import net.bytedev.bytestaff.util.ByteStaffChat;
import net.bytedev.bytestaff.util.ByteVanish;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

public class ByteStaffCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player CommandPlayer = (Player) sender;

        if (args[0].equalsIgnoreCase("vanish")) {
            ByteVanish.ByteVanishMain(CommandPlayer);
        }
        else if (args[0].equalsIgnoreCase("freeze")) {
            ByteFreezeWand.GiveFreezeWand(CommandPlayer);
        }
        else if (args[0].equalsIgnoreCase("invsee")) {

            ByteInvseeMenu.ByteOpenInvsee(CommandPlayer, args[1]);
        }
        else if (args[0].equalsIgnoreCase("staffchat")) {

            if (args.length > 1) {
                if (args.length >= 3) {
                    ByteStaffChat.ByteToggleSC(args[1], CommandPlayer, args[2]);
                }
                else {
                    ByteStaffChat.ByteToggleSC(args[1], CommandPlayer);
                }
            }
            else {
                CommandPlayer.sendMessage(Component.text("Invalid arguments!", NamedTextColor.RED));
            }

        }

        return false;
    }

}