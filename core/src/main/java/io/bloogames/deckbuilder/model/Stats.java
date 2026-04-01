package io.bloogames.deckbuilder.model;

public class Stats {
    private BaseStats baseStats;
    private int damage;

    public Stats(BaseStats baseStats) {
        this.baseStats = baseStats;
    }

    public BaseStats getBaseStats() {
        return baseStats;
    }

    public int getDamage() {
        return damage;
    }
}
