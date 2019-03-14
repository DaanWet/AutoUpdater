package me.damascus2000.autoupdater;


import org.bukkit.Bukkit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

public class Updater {


    public static void update() throws MalformedURLException {
        URL lijst = new URL("https://users.ugent.be/~fldbossc/cgi-bin/hash.py");
        try{
            String jsonString = new Scanner(lijst.openStream(), "UTF-8").useDelimiter("\\A").next();
            Bukkit.getLogger().info(jsonString);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            for (Object object : jsonObject.keySet()){
                String name = (String) object;
                Bukkit.getLogger().info(name);
                if (! name.contains("spigot")) {
                    download("https://users.ugent.be/~fldbossc/plugins/" + name, name);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }


    public static boolean download(String url, String name) throws Exception{
        URL download = new URL(url);
        try(BufferedInputStream in = new BufferedInputStream(download.openStream());
            FileOutputStream fileout = new FileOutputStream("tests" + System.getProperty("file.separator") + name)){
            byte[] buff = new byte[1024];
            int count = in.read(buff,0,1024);
            while (count != -1) {
                fileout.write(buff, 0, count);
                count = in.read(buff, 0, 1024);
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
