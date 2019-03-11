package me.damascus2000.autoupdater;

import org.bukkit.plugin.Plugin;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class Updater {


    public static void update(){

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
