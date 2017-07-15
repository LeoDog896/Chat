package me.tekoh.chat.API;

import org.bukkit.Bukkit;

import java.util.logging.Level;

/**
 * Created by Max on 15/07/2017.
 */

public class Logger {

    public void log(String message) {
        Bukkit.getLogger().log(Level.INFO, "§a[Chat] " + message);
    }

    public void error(String message) {
        Bukkit.getLogger().log(Level.SEVERE, "§c[Chat] " + message);
    }

}
