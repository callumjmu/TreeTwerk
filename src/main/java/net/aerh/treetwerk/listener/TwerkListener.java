package net.aerh.treetwerk.listener;

import net.aerh.treetwerk.TreeTwerkPlugin;
import net.aerh.treetwerk.player.PlayerManager;
import net.aerh.treetwerk.player.TPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TwerkListener implements Listener {

    private final FileConfiguration config = TreeTwerkPlugin.getInstance().getConfig();
    private final Set<UUID> cooldown = new HashSet<>();

    private final Material[] SAPLINGS = new Material[]{
            Material.ACACIA_SAPLING,
            Material.BIRCH_SAPLING,
            Material.JUNGLE_SAPLING,
            Material.DARK_OAK_SAPLING,
            Material.OAK_SAPLING,
            Material.SPRUCE_SAPLING
    };

    @EventHandler
    public void onShift(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        TPlayer tPlayer = PlayerManager.getInstance().getPlayer(player.getUniqueId());
        if (cooldown.contains(player.getUniqueId()) || tPlayer == null) return;

        boolean sneaking = event.isSneaking();
        if (!sneaking) return;

        if (!tPlayer.canGrow()) {
            tPlayer.addShifts(1);
            if (tPlayer.getShifts() >= TreeTwerkPlugin.getInstance().getConfig().getInt("min-shifts-required")) {
                if (TreeTwerkPlugin.isDebug())
                    TreeTwerkPlugin.getInstance().getLogger().info("Player " + player.getName() + "' can now grow trees");
                tPlayer.setCanGrow(true);
            }
            return;
        }

        Location location = player.getLocation();
        int radius = config.getInt("radius", 3);
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block relative = location.getBlock().getRelative(x, y, z);
                    Arrays.stream(SAPLINGS).filter(material -> relative.getType() == material).forEach(material -> {
                        if (TreeTwerkPlugin.isDebug())
                            TreeTwerkPlugin.getInstance().getLogger().info("Applied bone meal to " + material + " at " + formatLocationString(relative.getLocation()));
                        relative.applyBoneMeal(player.getFacing());
                        cooldown.add(player.getUniqueId());
                        Bukkit.getScheduler().runTaskLaterAsynchronously(TreeTwerkPlugin.getInstance(),
                                () -> cooldown.remove(player.getUniqueId()),
                                config.getLong("tick-cooldown", 10)
                        );
                    });
                }
            }
        }
    }

    private String formatLocationString(Location location) {
        return "(" + location.getX() + ", " + location.getY() + ", " + location.getZ() + ")";
    }
}
