package me.tekoh.chat;

import me.tekoh.chat.API.MuteChat;
import me.tekoh.chat.Commands.MuteChatCommand;
import me.tekoh.chat.Listeners.PlayerTalk;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Max on 11/07/2017.
 */

public class Core extends JavaPlugin {

    public MuteChat muteChat;

    public String getMessage(String position) {
        return getConfig().getString(position).replaceAll("&", "§").replaceAll("%prefix%", getConfig().getString("messages.prefix").replaceAll("&", "§"));
    }

    public boolean getBoolean(String position) {
        return getConfig().getBoolean(position);
    }

    @Override
    public void onEnable() {
        this.muteChat = new MuteChat();
        loadConfig();
        muteChat.setMute(false);
        registerEvents(this, new PlayerTalk(this));
        getCommand("mutechat").setExecutor(new MuteChatCommand(this));
        messageConsole("§aChat has successfully initialized..");
    }

    @Override
    public void onDisable() {
        muteChat.setMute(false);
    }

    public void messageConsole(String message) {
        getServer().getConsoleSender().sendMessage("[Chat] " + message);
    }

    private void registerEvents(Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void broadcast(String message) {
        getServer().broadcastMessage(message);
    }

}
