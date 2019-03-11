package me.damascus2000.autoupdater;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UpdateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0){
            Updater.update();
        }else {
            sender.sendMessage(ChatColor.DARK_RED + "This commands doesn't require arguments");
        }

        return false;
    }
}
