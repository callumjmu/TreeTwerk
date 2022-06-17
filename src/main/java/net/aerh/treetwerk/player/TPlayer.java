package net.aerh.treetwerk.player;

import net.aerh.treetwerk.TreeTwerkPlugin;

import java.util.UUID;

public class TPlayer {

    private final UUID uuid;
    private int shifts;

    public TPlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getId() {
        return uuid;
    }

    public boolean canGrow() {
        return getShifts() > TreeTwerkPlugin.getInstance().getConfig().getInt("min-shifts-required");
    }

    public int getShifts() {
        return shifts;
    }

    public void addShifts(int amount) {
        setShifts(getShifts() + amount);
    }

    public void setShifts(int shifts) {
        this.shifts = shifts;
    }
}
