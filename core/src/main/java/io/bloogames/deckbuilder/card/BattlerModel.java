package io.bloogames.deckbuilder.card;

import io.bloogames.deckbuilder.Stats;

public class BattlerModel {
    private BaseBattler base;
    private Stats stats;

    public BattlerModel(BaseBattler base) {
        stats = new Stats(base.getBaseStats());
        this.base = base;
    }

    public String getBattlerId() {
        return base.getBattlerId();
    }

    public int getPower() {
        return stats.getBaseStats().getPower();
    }

    public int getHealth() {
        return stats.getBaseStats().getHealth();
    }
}
