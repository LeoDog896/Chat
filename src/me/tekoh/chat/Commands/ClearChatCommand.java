package me.tekoh.chat.Commands;

import me.tekoh.chat.Core;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Max on 11/07/2017.
 */

public class ClearChatCommand implements CommandExecutor {

    private static Core pl;
    public ClearChatCommand(Core instance) {
        pl = instance;
    }

    private String name(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return "CONSOLE";
        }
        return ((Player) sender).getName();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("clearchat")) {
            if (args.length == 0) {
                if (!sender.hasPermission("chat.clearchat.global")) {
                    sender.sendMessage(pl.getMessage("messages.noperms"));
                    return true;
                }
                pl.clearChat.clearChat();
                Bukkit.getServer().broadcastMessage(pl.getMessage("messages.clearchat.clearline").replaceAll("%player%", name(sender)));
                return true;
            }
        }

        return true;
    }

}
