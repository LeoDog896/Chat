package me.tekoh.chat.Listeners;

import me.tekoh.chat.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

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
