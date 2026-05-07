package io.bloogames.deckbuilder.model.aura;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.target.concrete.BattlerTarget;
import io.bloogames.deckbuilder.effect.target.concrete.DamageableTarget;
import io.bloogames.deckbuilder.manager.TextManager;
import io.bloogames.deckbuilder.model.GameModel;
import io.bloogames.deckbuilder.model.damage.Damage;
import io.bloogames.deckbuilder.model.death.Death;
import io.bloogames.deckbuilder.text.Describable;
import io.bloogames.deckbuilder.text.DescriptionProperties;

public abstract class AuraModel implements Describable {
    private final String id;
    AuraOwner owner;
    DescriptionProperties descriptionProperties = new DescriptionProperties();

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
    public void beforeDamage(TargetContext<DamageableTarget> damageableTargetContext, Damage damage) {
    }

    public void afterDamage(TargetContext<DamageableTarget> damageableTargetContext, Damage damage) {
    }

    public void beforeDeath(GameModel game, DamageableTarget damageableTarget, Death death) {
    }

    public void afterDeath(GameModel game, DamageableTarget damageableTarget, Death death) {
    }

    public void onCalculateStats(GameModel game, BattlerTarget battler) {
    }

    @Override
    public String description() {
        registerProperties(descriptionProperties);
        return TextManager.INSTANCE.getAuraDescription(getId(), descriptionProperties);
    }

    protected abstract void registerProperties(DescriptionProperties properties);

    public abstract AuraModel copy();
}
