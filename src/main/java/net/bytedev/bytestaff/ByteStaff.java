package net.bytedev.bytestaff;

import net.bytedev.bytestaff.commands.ByteStaffCommand;
import net.bytedev.bytestaff.events.ByteClickEvent;
import net.bytedev.bytestaff.events.ByteGUIEvent;
import net.bytedev.bytestaff.events.OnChatEvent;
import net.bytedev.bytestaff.files.ByteConfig;
import net.bytedev.bytestaff.files.ByteStaffChatDB;
import net.bytedev.bytestaff.files.ByteWhitelistDB;
import net.bytedev.bytestaff.other.LicenseManager;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class ByteStaff extends JavaPlugin {

    public static ConsoleCommandSender AlertUtil;

    @Override
    public void onEnable() {

        ByteConfig.getInstance().load();

        LicenseManager.authenticate();

        System.out.println(Bukkit.getIp());


        AlertUtil = Bukkit.getServer().getConsoleSender();

        getCommand("bytestaff").setExecutor(new ByteStaffCommand());

        ByteStaffChatDB.CreateTable();
        ByteWhitelistDB.CreateWhitelistDB();

        getServer().getPluginManager().registerEvents(new ByteClickEvent(), this);
        getServer().getPluginManager().registerEvents(new ByteGUIEvent(), this);
        getServer().getPluginManager().registerEvents(new OnChatEvent(), this);

    }

    @Override
    public void onDisable() {

    }

    public static ByteStaff getInstance() {
        return getPlugin(ByteStaff.class);
    }

}