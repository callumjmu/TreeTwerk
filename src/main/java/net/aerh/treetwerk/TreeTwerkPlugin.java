package net.aerh.treetwerk;

import net.aerh.treetwerk.listener.SessionListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class TreeTwerkPlugin extends JavaPlugin {

    private static TreeTwerkPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        registerListeners();
    }

    @Override
    public void onDisable() {
        instance = null;
        // Plugin shutdown logic
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new SessionListener(), this);
    }

    public static boolean isDebug() {
        return instance.getConfig().getBoolean("debug");
    }

    public static TreeTwerkPlugin getInstance() {
        return instance;
    }
}
