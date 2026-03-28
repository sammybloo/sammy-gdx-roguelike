package io.bloogames.deckbuilder.card;

import io.bloogames.deckbuilder.BaseStats;

public class BaseBattler {
    private String battlerId;
    private String battlerName;
    private BaseStats baseStats;

    public BaseBattler(String battlerId, String name, BaseStats baseStats) {
        this.battlerId = battlerId;
        this.battlerName = name;
        this.baseStats = baseStats;
    }

    public String getBattlerId() {
        return battlerId;
    }

    public String getBattlerName() {
        return battlerName;
    }

    public BaseStats getBaseStats() {
        return baseStats;
    }
}
