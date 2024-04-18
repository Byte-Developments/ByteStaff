package net.bytedev.bytestaff;

import net.bytedev.bytestaff.commands.ByteStaffCommand;
import net.bytedev.bytestaff.events.ByteClickEvent;
import net.bytedev.bytestaff.events.ByteGUIEvent;
import net.bytedev.bytestaff.events.OnChatEvent;
import net.bytedev.bytestaff.files.ByteConfig;
import net.bytedev.bytestaff.files.ByteStaffChatDB;
import net.bytedev.bytestaff.files.ByteWhitelistDB;
import net.bytedev.bytestaff.menus.ByteSilentChest;
import net.bytedev.bytestaff.other.ByteStartup;
import net.bytedev.bytestaff.other.LicenseManager;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class ByteStaff extends JavaPlugin {

    public static ConsoleCommandSender AlertUtil;

    @Override
    public void onEnable() {

        ByteConfig.getInstance().load();

        ByteStartup.BytePluginStart();

    }

    @Override
    public void onDisable() {

        ByteStartup.BytePluginStop();

    }

    public static ByteStaff getInstance() {
        return getPlugin(ByteStaff.class);
    }

}