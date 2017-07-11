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

    int lines = pl.getInt("settings.clearchat.lines");

    public void clearChat() {
        for (int i = 0; i < lines; i++) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage("§r");
            }
        }
    }

}
