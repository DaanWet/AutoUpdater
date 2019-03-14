package me.damascus2000.autoupdater;

import jdk.nashorn.internal.runtime.ECMAException;
import org.bukkit.plugin.Plugin;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Updater {


    public static void update() throws MalformedURLException {
        URL lijst = new URL("https://users.ugent.be/~fldbossc/");
        try (BufferedInputStream in = new BufferedInputStream(lijst.openStream())){
            String jsonFile = "";


        } catch (IOException e){
            
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
