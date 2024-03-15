package net.bytedev.bytestaff.files;

import net.bytedev.bytestaff.ByteStaff;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class ByteConfig {

    private final static ByteConfig instance = new ByteConfig();
    private File file;
    private YamlConfiguration config;

    private ByteConfig() {
    }

    public Integer getInt(String IntLoc) { return this.config.getInt(IntLoc); }

    public List<String> getValList(String ValListName) { return this.config.getConfigurationSection(ValListName).getKeys(false).stream().toList(); }

    public void load() {
        file = new File(ByteStaff.getInstance().getDataFolder(),"config.yml");

        if (!file.exists()) {
            ByteStaff.getInstance().saveResource("config.yml", false);
        }

        config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getValue(String valLocation) {
        return config.getString(valLocation);
    }

    public List<String> getList(String listLocation) { return config.getStringList(listLocation); }

    public void save() {
        try {
            config.save(file);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void set(String path, Object value) {
        config.set(path, value);

        save();
    }

    public static ByteConfig getInstance() {
        return instance;
    }

}