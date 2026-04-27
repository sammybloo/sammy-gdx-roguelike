package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.data.BaseLeader;
import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.model.aura.Aura;
import io.bloogames.deckbuilder.model.aura.AuraSet;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public class LeaderModel implements Damageable {
    private final BaseLeader base;
    private int damage;
    private int currentMana;
    private final AuraSet auraSet;
    private final Ownership ownership;

    public LeaderModel(BaseLeader base, Ownership.Type owner) {
        this.base = base;
        this.currentMana = base.getMaxMana();
        this.auraSet = new AuraSet(base.getAuras());
        this.ownership = new Ownership(owner);
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

    public Ownership getOwnership() {
        return ownership;
    }

    public void addAllAuras(Array<Aura> arr) {
        arr.addAll(auraSet.getAuras());
    }

    public void spendMana(BattleModel battle, int amount) {
        currentMana -= amount;
        battle.dispatch(new GameEvent.ManaSpentEvent(this, amount));
    }

    @Override
    public int damage(int amount) {
        return damage += amount;
    }

    @Override
    public int heal(int amount) {
        return damage -= amount;
    }
}
