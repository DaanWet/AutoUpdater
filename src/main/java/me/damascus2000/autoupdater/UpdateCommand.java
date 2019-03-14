package me.damascus2000.autoupdater;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.net.MalformedURLException;

public class UpdateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0){
            try {
                Updater.update();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else {
            sender.sendMessage(ChatColor.DARK_RED + "This commands doesn't require arguments");
        }

        return false;
    }
}
