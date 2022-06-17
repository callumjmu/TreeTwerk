package net.aerh.treetwerk;

import net.aerh.treetwerk.listener.SessionListener;
import net.aerh.treetwerk.listener.TwerkListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class TreeTwerkPlugin extends JavaPlugin {

    private static TreeTwerkPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        if (getConfig().getBoolean("enabled")) registerListeners();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new SessionListener(), this);
        getServer().getPluginManager().registerEvents(new TwerkListener(), this);
    }

    public static boolean isDebug() {
        return instance.getConfig().getBoolean("debug");
    }

    public static TreeTwerkPlugin getInstance() {
        return instance;
    }
}
