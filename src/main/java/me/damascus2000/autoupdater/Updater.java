package me.damascus2000.autoupdater;


import org.bukkit.Bukkit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Updater {


    public static void update() throws MalformedURLException {
        URL lijst = new URL("https://users.ugent.be/~fldbossc/");
        try{
            String jsonString = new Scanner(lijst.openStream(), "UTF-8").useDelimiter("\\A").next();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            for (Object object : jsonObject.values()){
                String name = (String) object;
                Bukkit.getConsoleSender().sendMessage(name);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }


    public boolean download(String url) throws Exception{
        URL download = new URL(url);
        try(BufferedInputStream in = new BufferedInputStream(download.openStream());
            FileOutputStream fileout = new FileOutputStream("plugins" + System.getProperty("file.separator") + download.getFile())){
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
