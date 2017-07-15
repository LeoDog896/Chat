package me.tekoh.chat.Listeners;

import me.tekoh.chat.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

/**
 * Created by Max on 12/07/2017.
 */

public class PreCommand implements Listener {

    private static Core pl;
    public PreCommand(Core instance) {
        pl = instance;
    }

    @EventHandler
    public void blockCommandMutedChat(PlayerCommandPreprocessEvent e) {

        if (pl.muteChat.isMuted()) {

            if (e.getPlayer().hasPermission("chat.mutechat.bypass")) {
                return;
            }

            List<String> blockedcommands = pl.getConfig().getStringList("settings.mutechat.blockedcommands");

            for (String cmd : blockedcommands) {
                if (e.getMessage().toLowerCase().startsWith("/" + cmd)) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(pl.getMessage("messages.mutechat.chatismuted"));
                    return;
                }
            }
        }
    }

    @EventHandler
    public void blockCommand(PlayerCommandPreprocessEvent e) {

        if (!pl.getBoolean("settings.enable.commandblock")) {
            return;
        }

        if (e.getPlayer().hasPermission("chat.commandblock.bypass")) {
            return;
        }

        if (pl.getBoolean("settings.commandblock.blockcolons")) {
            if (e.getMessage().contains(":")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(pl.getMessage("messages.commandblock.nocolons"));
                pl.logger.log(e.getPlayer().getName() + " tried to use the command " + e.getMessage());
                return;
            }
        }

        if (!pl.getBoolean("settings.commandblock.useperworld")) {
            List<String> cmds = pl.getConfig().getStringList("settings.commandblock.blockedcommands");

            for (String command : cmds) {
                if (e.getMessage().toLowerCase().startsWith("/" + command)) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(pl.getMessage("messages.commandblock.blockedcommand"));
                    pl.logger.log(e.getPlayer().getName() + " tried to use the command " + e.getMessage());
                    return;
                }
            }
        } else {
            if (pl.getConfig().getStringList("settings.commandblock." + e.getPlayer().getWorld().getName().toLowerCase()) == null) {
                pl.logger.error("The world '" + e.getPlayer().getWorld().getName().toLowerCase() + "' doesn't exist in the config");
                return;
            }

            List<String> cmds = pl.getConfig().getStringList("settings.commandblock." + e.getPlayer().getWorld().getName().toLowerCase());

            for (String command : cmds) {
                if (e.getMessage().toLowerCase().startsWith("/" + command)) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(pl.getMessage("messages.commandblock.blockedcommand"));
                    pl.logger.log(e.getPlayer().getName() + " tried to use the command " + e.getMessage());
                    return;
                }
            }
        }

    }

}
