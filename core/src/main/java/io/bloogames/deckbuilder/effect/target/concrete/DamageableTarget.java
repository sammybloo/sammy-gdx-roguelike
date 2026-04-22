package io.bloogames.deckbuilder.effect.target.concrete;

import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.model.Damageable;
import io.bloogames.deckbuilder.model.PartyModel;

public abstract class DamageableTarget implements Target {
    private final Damageable damageable;
    private final PartyModel owner;

    public DamageableTarget(Damageable damageable, PartyModel owner) {
        this.damageable = damageable;
        this.owner = owner;
    }

    public Damageable damageable() {
        return damageable;
    }

    @Override
    public PartyModel owner() {
        return owner;
    }
}
