package net.bytedev.bytestaff.commands;

import net.bytedev.bytestaff.util.ByteVanish;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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

        return false;
    }
}
