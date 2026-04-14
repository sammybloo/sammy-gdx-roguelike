package io.bloogames.deckbuilder.effect.target.concrete;

import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.Damageable;
import io.bloogames.deckbuilder.model.BattlePartyModel;

public final class DamageableTarget implements Target {
    private final Damageable damageable;
    private final BattlePartyModel owner;

    public DamageableTarget(Damageable damageable, BattlePartyModel owner) {
        this.damageable = damageable;
        this.owner = owner;
    }

    @Override
    public TargetType type() {
        return TargetType.DAMAGEABLE;
    }

    public Damageable damageable() {
        return damageable;
    }

    @Override
    public BattlePartyModel owner() {
        return owner;
    }
}
