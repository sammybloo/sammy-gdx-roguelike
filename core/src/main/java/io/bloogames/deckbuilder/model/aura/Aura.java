package io.bloogames.deckbuilder.model.aura;

import io.bloogames.deckbuilder.damage.Damage;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.target.concrete.BattlerTarget;
import io.bloogames.deckbuilder.effect.target.concrete.DamageableTarget;
import io.bloogames.deckbuilder.model.GameModel;
import io.bloogames.deckbuilder.model.StatsModel;

public abstract class Aura {
    private final String id;
    AuraOwner owner;

    public Aura(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public AuraOwner getOwner() {
        return owner;
    }

    public void setOwner(AuraOwner owner) {
        this.owner = owner;
    }

    // NOTE: should ONLY mutate the damage object.
    public void beforeDamage(TargetContext<DamageableTarget> damageableTargetContext, Damage damage) {}
    public void afterDamage(TargetContext<DamageableTarget> damageableTargetContext, Damage damage) {}
    public void onCalculateStats(BattlerTarget battler, StatsModel stats, GameModel game) {}

    public abstract Aura copy();
}
