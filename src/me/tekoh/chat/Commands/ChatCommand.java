package me.tekoh.chat.Commands;

import me.tekoh.chat.Core;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Max on 15/07/2017.
 */

public class ChatCommand implements CommandExecutor {

    private static Core pl;
    public ChatCommand(Core instance) {
        pl = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("chat")) {
            if (!sender.hasPermission("chat.reload")) {
                sender.sendMessage(pl.getMessage("messages.noperms"));
                return true;
            }
            if (args.length != 1) {
                sender.sendMessage("§cUsage: /chat reload");
                return true;
            }
            if (!args[0].equalsIgnoreCase("reload")) {
                sender.sendMessage("§cUsage: /chat reload");
                return true;
            }
            pl.loadConfig();
            pl.reloadConfig();
            sender.sendMessage("§a§lconfig.yml reloaded");
            pl.logger.log("config.yml reloaded");
            return true;
        }

        return true;
    }

}
