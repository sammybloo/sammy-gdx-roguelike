package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.data.BaseLeader;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.event.GameEvent;

public class LeaderModel implements Damageable {
    private BaseLeader base;
    private int damage;
    private int currentMana;
    private Array<Aura> auras;

    public LeaderModel(BaseLeader base) {
        this.base = base;
        this.currentMana = base.getMaxMana();
        this.auras = base.getAuras();
    }

    public int getMaxHealth() {
        return base.getMaxHealth();
    }

    public int getCurrentHealth() {
        return getMaxHealth() - damage;
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

    public void addAllAuras(Array<Aura> arr) {
        arr.addAll(auras);
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
