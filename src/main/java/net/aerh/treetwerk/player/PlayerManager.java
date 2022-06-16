package net.aerh.treetwerk.player;

import net.aerh.treetwerk.TreeTwerkPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerManager {

    private static final PlayerManager instance = new PlayerManager();
    private final List<TPlayer> players = new ArrayList<>();

    public PlayerManager() {
    }

    public static PlayerManager getInstance() {
        return instance;
    }

    public void addPlayer(UUID uuid) {
        TPlayer tPlayer = createPlayer(uuid);
        addPlayer(tPlayer);
    }

    public void addPlayer(TPlayer tPlayer) {
        if(TreeTwerkPlugin.isDebug()) TreeTwerkPlugin.getInstance().getLogger().info("Adding TPlayer '" + tPlayer.getId() + "'");
        players.add(tPlayer);
    }

    public void removePlayer(UUID uuid) {
        TPlayer tPlayer = getPlayer(uuid);
        removePlayer(tPlayer);
    }

    public void removePlayer(TPlayer tPlayer) {
        if(TreeTwerkPlugin.isDebug()) TreeTwerkPlugin.getInstance().getLogger().info("Removing TPlayer '" + tPlayer.getId() + "'");
        players.remove(tPlayer);
    }

    public TPlayer createPlayer(UUID uuid) {
        TPlayer tPlayer = new TPlayer(uuid);
        if(players.contains(tPlayer)) throw new RuntimeException("Player already initialized");
        return new TPlayer(uuid);
    }

    public TPlayer getPlayer(UUID uuid) {
        return players.stream().filter(tPlayer -> tPlayer.getId().equals(uuid)).findFirst().orElse(null);
    }

    public List<TPlayer> getPlayers() {
        return players;
    }
}
