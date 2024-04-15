package net.bytedev.bytestaff.events;

import net.bytedev.bytestaff.files.ByteStaffChatDB;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnChatEvent implements Listener {

    @EventHandler
    public void OnChat(AsyncPlayerChatEvent event) {

        Player ChatPlayer = event.getPlayer();

        if (ByteStaffChatDB.CheckStaffChat(ChatPlayer.getUniqueId().toString())) {

            event.setCancelled(true);

            for (Player LoopSCPlayer : Bukkit.getOnlinePlayers()) {

                if (ByteStaffChatDB.CheckStaffChat(LoopSCPlayer.getUniqueId().toString())) {

                    LoopSCPlayer.sendMessage(Component
                            .text("[", NamedTextColor.DARK_GRAY)
                            .append(Component.text("StaffChat", NamedTextColor.GOLD))
                            .append(Component.text("] ", NamedTextColor.DARK_GRAY))
                            .append(Component.text(ChatPlayer.getName(), NamedTextColor.YELLOW))
                            .append(Component.text(" → ", NamedTextColor.DARK_GRAY))
                            .append(Component.text(event.getMessage(), NamedTextColor.WHITE))
                    );
                }
            }
        }
    }
}