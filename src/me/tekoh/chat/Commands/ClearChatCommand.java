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

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("clearchat")) {
            if (args.length == 0) {
                if (!pl.getBoolean("settings.enable.clearchat")) {
                    sender.sendMessage("§cClearChat is disabled.");
                    return true;
                }
                if (!sender.hasPermission("chat.clearchat.global")) {
                    sender.sendMessage(pl.getMessage("messages.noperms"));
                    return true;
                }
                pl.clearChat.clearChat();
                Bukkit.getServer().broadcastMessage(pl.getMessage("messages.clearchat.clearline").replaceAll("%player%", sender.getName()));
                return true;
            }
            if (args.length > 1) {
                sender.sendMessage(pl.getMessage("messages.toomanyargs"));
                return true;
            }
            if (args.length == 1) {
                if (!sender.hasPermission("chat.clearchat.personal")) {
                    sender.sendMessage(pl.getMessage("messages.noperms"));
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage("§cError: Null target.");
                    return true;
                }
                pl.clearChat.clearChatPersonal(target);
                target.sendMessage(pl.getMessage("messages.clearchat.clearlineplayer").replaceAll("%player%", sender.getName()));
                sender.sendMessage(pl.getMessage("messages.done"));
                return true;
            }
        }

        return true;
    }

}
