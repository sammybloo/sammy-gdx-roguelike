package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.data.BaseStats;

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
