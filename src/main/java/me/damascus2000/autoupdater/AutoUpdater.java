package me.damascus2000.autoupdater;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class AutoUpdater extends JavaPlugin {



    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            Updater.update(this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bukkit.getLogger().info("Done Loading");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
