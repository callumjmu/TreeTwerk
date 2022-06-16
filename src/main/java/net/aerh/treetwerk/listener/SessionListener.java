package net.aerh.treetwerk.listener;

import net.aerh.treetwerk.player.PlayerManager;
import net.aerh.treetwerk.player.TPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SessionListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        TPlayer tPlayer = PlayerManager.getInstance().createPlayer(player.getUniqueId());
        PlayerManager.getInstance().addPlayer(tPlayer);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        TPlayer tPlayer = PlayerManager.getInstance().getPlayer(player.getUniqueId());
        PlayerManager.getInstance().removePlayer(tPlayer);
    }
}
