package net.bytedev.bytestaff.other;

import net.bytedev.bytestaff.ByteStaff;
import net.bytedev.bytestaff.commands.ByteStaffCommand;
import net.bytedev.bytestaff.events.ByteClickEvent;
import net.bytedev.bytestaff.events.ByteGUIEvent;
import net.bytedev.bytestaff.events.OnChatEvent;
import net.bytedev.bytestaff.files.ByteStaffChatDB;
import net.bytedev.bytestaff.files.ByteWhitelistDB;
import net.bytedev.bytestaff.menus.ByteSilentChest;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.bukkit.Bukkit.getPluginsFolder;
import static org.bukkit.Bukkit.getServer;

public class ByteStartup {

    public static void BytePluginStart() {

        File DataDir = new File(ByteStaff.getInstance().getDataFolder().getAbsolutePath() + "/data");

        if (!DataDir.exists()) {

            DataDir.mkdirs();
        }

        LicenseManager.authenticate();


        ByteStaff.AlertUtil = getServer().getConsoleSender();

        ByteStaff.getInstance().getCommand("bytestaff").setExecutor(new ByteStaffCommand());

        ByteStaffChatDB.CreateTable();
        ByteWhitelistDB.CreateWhitelistDB();

        ByteStaff.getInstance().getServer().getPluginManager().registerEvents(new ByteClickEvent(), ByteStaff.getInstance());
        ByteStaff.getInstance().getServer().getPluginManager().registerEvents(new ByteGUIEvent(), ByteStaff.getInstance());
        ByteStaff.getInstance().getServer().getPluginManager().registerEvents(new OnChatEvent(), ByteStaff.getInstance());
        ByteStaff.getInstance().getServer().getPluginManager().registerEvents(new ByteSilentChest(), ByteStaff.getInstance());

    }

    public static void BytePluginStop() {

    }
}