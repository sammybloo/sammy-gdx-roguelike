package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.data.BaseLeader;

public class LeaderModel implements Damageable {
    private BaseLeader base;
    private int damage;
    private int currentMana;

    public LeaderModel(BaseLeader base) {
        this.base = base;
        this.currentMana = base.getMaxMana();
    }

    public int getMaxHealth() {
        return base.getMaxHealth();
    }

    public int getDamage() {
        return damage;
    }

    public String getId() {
        return base.getId();
    }

    public int getCurrentMana() {
        return currentMana;
    }
}
