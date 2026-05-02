package io.bloogames.deckbuilder.model.aura;

import io.bloogames.deckbuilder.model.damage.Damage;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.target.concrete.BattlerTarget;
import io.bloogames.deckbuilder.effect.target.concrete.DamageableTarget;
import io.bloogames.deckbuilder.model.GameModel;
import io.bloogames.deckbuilder.model.death.Death;

public abstract class AuraModel {
    private final String id;
    AuraOwner owner;

    public AuraModel(String id) {
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
    public void beforeDeath(GameModel game, DamageableTarget damageableTarget, Death death) {}
    public void afterDeath(GameModel game, DamageableTarget damageableTarget, Death death) {}
    public void onCalculateStats(GameModel game, BattlerTarget battler) {}

    public abstract AuraModel copy();
}
