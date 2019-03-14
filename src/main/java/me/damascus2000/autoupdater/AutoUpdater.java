package me.damascus2000.autoupdater;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOError;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public final class AutoUpdater extends JavaPlugin {

    private static File pluginsYml;
    private static YamlConfiguration pluginsConfig = null;


    @Override
    public void onEnable() {
        // Plugin startup logic
        pluginsYml = new File(getDataFolder(), "plugins.yml");
        try{
            Updater.update();
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        getCommand("update").setExecutor(new UpdateCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
