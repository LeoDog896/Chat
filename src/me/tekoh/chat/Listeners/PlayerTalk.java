package me.tekoh.chat.Listeners;

import me.tekoh.chat.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Max on 11/07/2017.
 */

public class PlayerTalk implements Listener {

    private static Core pl;
    public PlayerTalk(Core instance) {
        pl = instance;
    }

    @EventHandler
    public void onTalk(AsyncPlayerChatEvent e) {

        String message = e.getMessage();

        //URL/IP Blocker
        Pattern pattern = Pattern.compile("(?i)(((([a-zA-Z0-9-]+\\.)+(de|eu|com|net|org|to|gs|me|info|biz|tv|au))|([0-9]{1,3}\\.){3}[0-9]{1,3})(\\:[0-9]{2,5})?)");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            if (!pl.getBoolean("settings.enable.urlblock")) {
                return;
            }
            if (e.getPlayer().hasPermission("chat.urlblock.bypass")) {
                return;
            }
            e.setCancelled(true);
            e.getPlayer().sendMessage(pl.getMessage("messages.urlblocked"));
            pl.messageConsole(pl.getMessage("messages.prefix") + " §a" + e.getPlayer().getName() + " §7tried to type: §c" + message);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("chat.urlblock.notify")) {
                    player.sendMessage(pl.getMessage("messages.prefix") + " §a" + e.getPlayer().getName() + " §7tried to type: §c" + message);
                }
            }
        }

        //MuteChat
        if (pl.muteChat.isMuted()) {
            if (e.getPlayer().hasPermission("chat.mutechat.bypass")) {
                return;
            }
            e.setCancelled(true);
            e.getPlayer().sendMessage(pl.getMessage("messages.mutechat.chatismuted"));
            pl.messageConsole(e.getPlayer().getName() + " attempted to send a message, but the chat is currently muted.");
        }


    }

}
