package net.bytedev.bytestaff.events;

import net.bytedev.bytestaff.ByteStaff;
import net.bytedev.bytestaff.util.ByteFreeze;
import net.bytedev.bytestaff.util.ByteMetaUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.HashMap;
import java.util.UUID;

public class ByteClickEvent implements Listener {

    private final HashMap<UUID, Long> cooldowns = new HashMap<>();
    private final long cooldownTime = 1000;

    @EventHandler
    public void onPlayerUse(PlayerInteractAtEntityEvent InteractEvent) {
        Player InteractPlayer = InteractEvent.getPlayer();

        if (InteractEvent == null || InteractEvent.getRightClicked().getType() != EntityType.PLAYER) {
            sendMessageOnce(InteractPlayer, "You can only freeze players!");
            return;
        }

        if (!ByteMetaUtil.ByteGetMeta(InteractPlayer.getInventory().getItemInMainHand().getItemMeta(), "bytestaff-freeze-item") || !InteractPlayer.hasPermission("bytestaff.freeze.use")) {
            return;
        }

        UUID playerId = InteractPlayer.getUniqueId();
        if (cooldowns.containsKey(playerId) && System.currentTimeMillis() - cooldowns.get(playerId) < cooldownTime) {

            long timeLeft = (cooldownTime - (System.currentTimeMillis() - cooldowns.get(playerId))) / 1000;
            sendMessageOnce(InteractPlayer, "You can only freeze a player once every 10 seconds. Please wait " + timeLeft + " seconds.");
        }
        else {
            ByteFreeze.ByteFreezeMain(((Player) InteractEvent.getRightClicked()));
            cooldowns.put(playerId, System.currentTimeMillis());
        }
    }

    private void sendMessageOnce(Player player, String message) {
        if (!player.getMetadata("messageSent").isEmpty()) {
            player.sendMessage(Component.text(message));
            player.setMetadata("messageSent", new FixedMetadataValue(ByteStaff.getInstance(), true));
        }
    }
}