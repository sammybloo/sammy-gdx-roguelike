package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.data.BaseLeader;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.event.GameEvent;

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

    public int getMaxMana() {
        return base.getMaxMana();
    }

    public void spendMana(BattleModel battle, int amount) {
        currentMana -= amount;
        battle.dispatch(new GameEvent.ManaSpentEvent(this, amount));
    }

    @Override
    public int damage(TargetContext<?> context, int amount) {
        return damage += amount;
    }

    @Override
    public int heal(TargetContext<?> context, int amount) {
        return damage -= amount;
    }
}
