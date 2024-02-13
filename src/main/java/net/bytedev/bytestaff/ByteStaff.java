package net.bytedev.bytestaff;

import net.bytedev.bytestaff.commands.ByteStaffCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class ByteStaff extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("bytestaff").setExecutor(new ByteStaffCommand());

    }

    @Override
    public void onDisable() {

    }
}
