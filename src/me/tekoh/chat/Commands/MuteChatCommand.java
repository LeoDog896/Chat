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

public class MuteChatCommand implements CommandExecutor {

    private static Core pl;
    public MuteChatCommand(Core instance) {
        pl = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("mutechat")) {
            if (!sender.hasPermission("chat.mutechat.toggle")) {
                sender.sendMessage(pl.getMessage("messages.noperms"));
                return true;
            }
            if (args.length == 0) {
                if (pl.muteChat.isMuted()) {
                    pl.muteChat.setMute(false);
                    pl.broadcast(pl.getMessage("messages.mutechat.nolongermuted"));
                    return true;
                } else {
                    pl.muteChat.setMute(true);
                    pl.broadcast(pl.getMessage("messages.mutechat.isnowmuted"));
                    return true;
                }
            }
        }

        return true;
    }

}
