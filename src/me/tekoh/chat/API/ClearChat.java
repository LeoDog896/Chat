package me.tekoh.chat.API;

import me.tekoh.chat.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Max on 11/07/2017.
 */

public class ClearChat {

    private static Core pl;
    public ClearChat(Core instance) {
        pl = instance;
    }

    private int lines = 250;

    public void clearChat() {
        for (int i = 0; i < lines; i++) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("chat.clearchat.bypass")) {
                    return;
                }
                player.sendMessage("§r");
            }
        }
    }

    public void clearChatPersonal(Player p) {
        for (int i = 0; i < lines; i++) {
            p.sendMessage("§r");
        }
    }

}
