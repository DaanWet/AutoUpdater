package me.damascus2000.autoupdater;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Updater {


    public static void update(Plugin pl) throws MalformedURLException {
        URL lijst = new URL("https://users.ugent.be/~dwettinc/cgi-bin/hash.py");
        try {
            String jsonString = new Scanner(lijst.openStream(), "UTF-8").useDelimiter("\\A").next();
            Bukkit.getLogger().info(jsonString);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            Set<String> keySet = jsonObject.keySet();
            ArrayList<String> currentPlugins = getPluginList(pl);
            for (Object object : jsonObject.keySet()) {
                String name = (String) object;
                boolean verschillend = true;
                if (currentPlugins.contains(name)) {
                    String webhash = ((String) jsonObject.get(name)).toLowerCase();
                    String currenthash = calculateHash(name).toLowerCase();
                    verschillend = !currenthash.equalsIgnoreCase(webhash);
                }
                if (!name.equalsIgnoreCase("AutoUpdater.jar") && verschillend) {
                    download("https://users.ugent.be/~dwettinc/plugins/" + name, name);
                    Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Updated " + name);
                }
            }
            for (String plugin : currentPlugins) {
                if (!keySet.contains(plugin) && !plugin.contains("Updater")) {
                    try {
                        Files.delete(Paths.get(pl.getDataFolder().getAbsoluteFile().getParent() + System.getProperty("file.separator") + plugin));
                        Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + "Deleted " + plugin);
                    } catch (Exception e) {
                        Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + "Unable to delete " + plugin);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getServer().broadcastMessage(ChatColor.RED + "Unable to update all plugins");
        }

    }

    public static ArrayList<String> getPluginList(Plugin pl) {
        ArrayList<String> lijst = new ArrayList<>();
        File folder = new File(pl.getDataFolder().getAbsoluteFile().getParent());
        File[] filelist = folder.listFiles();
        for (File file : filelist) {
            if (file.isFile()) {
                lijst.add(file.getName());
            }
        }
        return lijst;
    }

    public static void download(String url, String name) throws Exception {
        URL download = new URL(url);
        try (BufferedInputStream in = new BufferedInputStream(download.openStream());
             FileOutputStream fileout = new FileOutputStream("plugins" + System.getProperty("file.separator") + name)) {
            byte[] buff = new byte[1024];
            int count = in.read(buff, 0, 1024);
            while (count != -1) {
                fileout.write(buff, 0, count);
                count = in.read(buff, 0, 1024);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String calculateHash(String plugin) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        try (InputStream is = Files.newInputStream(Paths.get("plugins" + System.getProperty("file.separator") + plugin));
             DigestInputStream dis = new DigestInputStream(is, md)) {
            byte[] buff = new byte[1024];
            int count = dis.read(buff);
            while (count > 0) {
                count = dis.read(buff, 0, 1024);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest);

    }
}
