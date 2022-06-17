package net.aerh.treetwerk.player;

import java.util.UUID;

public class TPlayer {

    private final UUID uuid;
    private int shifts;
    private boolean grow;

    public TPlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getId() {
        return uuid;
    }

    public boolean canGrow() {
        return grow;
    }

    public void setCanGrow(boolean grow) {
        this.grow = grow;
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
