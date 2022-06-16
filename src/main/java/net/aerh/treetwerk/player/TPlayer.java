package net.aerh.treetwerk.player;

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
