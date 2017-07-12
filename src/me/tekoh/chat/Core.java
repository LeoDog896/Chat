package me.tekoh.chat;

import me.tekoh.chat.API.ClearChat;
import me.tekoh.chat.API.MuteChat;
import me.tekoh.chat.Commands.ClearChatCommand;
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
    public ClearChat clearChat;

    public String getMessage(String position) {
        return getConfig().getString(position).replaceAll("&", "§").replaceAll("%prefix%", getConfig().getString("messages.prefix").replaceAll("&", "§"));
    }

    public boolean getBoolean(String position) {
        return getConfig().getBoolean(position);
    }

    @Override
    public void onEnable() {

        //Checking config version
        if (getConfig().getInt("configversion") < 0.1) {
            messageConsole("§c~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            messageConsole("§c[Chat] You must delete your config as the latest update has changed it.");
            messageConsole("§c[Chat] We recommend backing up your old config to copy some settings back over");
            messageConsole("§c~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            messageConsole("[Chat] Disabling..");
            getServer().getPluginManager().disablePlugin(this);
        }


        this.muteChat = new MuteChat();
        this.clearChat = new ClearChat(this);
        loadConfig();
        muteChat.setMute(false);
        registerEvents(this, new PlayerTalk(this));
        getCommand("mutechat").setExecutor(new MuteChatCommand(this));
        getCommand("clearchat").setExecutor(new ClearChatCommand(this));
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
