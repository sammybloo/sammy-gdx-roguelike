package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.data.BaseStats;

public class StatsModel {
    private final BaseStats baseStats;

    public StatsModel(BaseStats baseStats) {
        this.baseStats = baseStats;
    }

    public BaseStats getBaseStats() {
        return baseStats;
    }
}
