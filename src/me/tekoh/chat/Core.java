package me.tekoh.chat;

import me.tekoh.chat.API.ClearChat;
import me.tekoh.chat.API.Logger;
import me.tekoh.chat.API.MuteChat;
import me.tekoh.chat.Commands.ChatCommand;
import me.tekoh.chat.Commands.ClearChatCommand;
import me.tekoh.chat.Commands.MuteChatCommand;
import me.tekoh.chat.Listeners.PlayerTalk;
import me.tekoh.chat.Listeners.PreCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Max on 11/07/2017.
 */

public class Core extends JavaPlugin {

    public MuteChat muteChat;
    public ClearChat clearChat;
    public Logger logger;

    public String getMessage(String position) {
        return getConfig().getString(position).replaceAll("&", "§").replaceAll("%prefix%", getConfig().getString("messages.prefix").replaceAll("&", "§"));
    }

    public boolean getBoolean(String position) {
        return getConfig().getBoolean(position);
    }

    @Override
    public void onEnable() {
        this.muteChat = new MuteChat();
        this.clearChat = new ClearChat(this);
        this.logger = new Logger();

        loadConfig();

        muteChat.setMute(false);

        registerEvents(new PlayerTalk(this), new PreCommand(this));

        getCommand("mutechat").setExecutor(new MuteChatCommand(this));
        getCommand("clearchat").setExecutor(new ClearChatCommand(this));
        getCommand("chat").setExecutor(new ChatCommand(this));

        logger.log("Chat has successfully initialized..");
    }

    private void registerEvents(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    public void loadConfig() {
        saveDefaultConfig();
    }

    public void broadcast(String message) {
        getServer().broadcastMessage(message);
    }

}
