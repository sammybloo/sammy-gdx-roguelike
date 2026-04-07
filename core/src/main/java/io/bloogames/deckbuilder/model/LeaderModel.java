package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.data.BaseLeader;
import io.bloogames.deckbuilder.effect.EffectContext;

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

    @Override
    public int damage(EffectContext<?> context, int amount) {
        return damage += amount;
    }

    @Override
    public int heal(EffectContext<?> context, int amount) {
        return damage -= amount;
    }
}
