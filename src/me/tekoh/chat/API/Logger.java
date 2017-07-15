package me.tekoh.chat.API;

import org.bukkit.Bukkit;

import java.util.logging.Level;

/**
 * Created by Max on 15/07/2017.
 */

public class Logger {

    public void log(String message) {
        Bukkit.getConsoleSender().sendMessage("§a[Chat] " + message);
    }

    public void error(String message) {
        Bukkit.getConsoleSender().sendMessage("§c[Chat] ERROR: " + message);
    }

}
