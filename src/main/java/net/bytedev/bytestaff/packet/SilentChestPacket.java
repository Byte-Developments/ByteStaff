package net.bytedev.bytestaff.packet;

import net.bytedev.bytestaff.ByteStaff;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;

import java.util.ArrayList;
import java.util.List;

public class SilentChestPacket {

    public static void setupAnimationListener() {
        ProtocolLibrary.getProtocolManager().addPacketListener(
                new PacketAdapter(ByteStaff.getInstance(), ListenerPriority.HIGH,
                        PacketType.Play.Server.BLOCK_ACTION) {
                    @Override
                    public void onPacketSending(PacketEvent e) {
                        if (e.getPacketType() == PacketType.Play.Server.BLOCK_ACTION) {
                            Player listener = e.getPlayer();
                            // is the action the chest-opening-action?
                            if (e.getPacket().getIntegers().read(1) != 1)
                                return;
                            BlockPosition position = e.getPacket()
                                    .getBlockPositionModifier().read(0);
                            if (position == null)
                                return;
                            Location loc = position.toVector().toLocation(
                                    listener.getWorld());
                            Block b = listener.getWorld().getBlockAt(loc);
                            if (!(b.getState() instanceof Chest)) {
                                return;
                            }
                            Chest chest = (Chest) b.getState();
                            Inventory inv = chest.getBlockInventory();
                            List<HumanEntity> humanViewers = inv.getViewers();
                            List<Player> players = new ArrayList<>();
                            for (HumanEntity entity : humanViewers) {
                                if (entity instanceof Player)
                                    players.add((Player) entity);
                            }
                            for (Player p : players) {
                                if (p.hasMetadata("ByteVanish")) {
                                    // cancel it since one of the viewers is
                                    // invisible
                                    e.setCancelled(true);
                                }
                            }
                        }
                    }
                });
    }

    public static void setupSoundListener() {
        ProtocolLibrary.getProtocolManager().addPacketListener(
                new PacketAdapter(ByteStaff.getInstance(), ListenerPriority.HIGH,
                        PacketType.Play.Server.NAMED_SOUND_EFFECT) {
                    @Override
                    public void onPacketSending(PacketEvent e) {
                        if (e.getPacketType() == PacketType.Play.Server.NAMED_SOUND_EFFECT) {
                            Player listener = e.getPlayer();
                            // is the action the chest-opening-sound?
                            if (!(e.getPacket().getStrings().read(0)
                                    .contains("chest")))
                                return;
                            // divide the location by 8, since it's a bit
                            // obfuscated
                            Location loc = new Location(listener.getWorld(), e
                                    .getPacket().getIntegers().read(0) / 8, e
                                    .getPacket().getIntegers().read(1) / 8, e
                                    .getPacket().getIntegers().read(2) / 8);
                            Block b = listener.getWorld().getBlockAt(loc);
                            check: if (!(b.getState() instanceof Chest)) {
                                // search for adjacent blocks, too, since the
                                // position of the animation is not exact
                                List<Location> adjacentBlockLocations = getAdjacentBlockLocations(loc);
                                for (Location otherLocation : adjacentBlockLocations) {
                                    Block otherBlock = listener.getWorld()
                                            .getBlockAt(otherLocation);
                                    if (otherBlock.getState() instanceof Chest) {
                                        b = otherBlock;
                                        loc = otherLocation;
                                        break check;
                                    }
                                }
                                return;
                            }
                            if (e.getPacket().getStrings().read(0)
                                    .equalsIgnoreCase("random.chestclosed")) {
                                for (Player p : listener.getWorld()
                                        .getPlayers()) {
                                    if (p.hasMetadata("ByteVanish")
                                            && p.getLocation().distance(loc) < 6) {
                                        // cancel it since an invisible player
                                        // is
                                        // nearby
                                        e.setCancelled(true);
                                    }
                                }
                                return;
                            }
                            Chest chest = (Chest) b.getState();
                            Inventory inv = chest.getBlockInventory();
                            List<HumanEntity> humanViewers = inv.getViewers();
                            for (HumanEntity entity : humanViewers) {
                                if (entity instanceof Player)
                                    if (((Player) entity).hasMetadata("ByteVanish")) {
                                        // cancel it since one of the viewers is
                                        // invisible
                                        e.setCancelled(true);
                                    }
                            }
                        }
                    }
                });
    }

    private static Location add(Location l, int x, int z) {
        return new Location(l.getWorld(), l.getX() + x, l.getY(), l.getZ() + z);
    }

    private static List<Location> getAdjacentBlockLocations(Location loc) {
        List<Location> adjacentBlockLocations = new ArrayList<>();
        adjacentBlockLocations.add(add(loc, 1, 0));
        adjacentBlockLocations.add(add(loc, -1, 0));
        adjacentBlockLocations.add(add(loc, 0, -1));
        adjacentBlockLocations.add(add(loc, 0, 1));
        adjacentBlockLocations.add(add(loc, 1, 1));
        adjacentBlockLocations.add(add(loc, -1, -1));
        adjacentBlockLocations.add(add(loc, 1, -1));
        adjacentBlockLocations.add(add(loc, -1, 1));
        return adjacentBlockLocations;
    }

}