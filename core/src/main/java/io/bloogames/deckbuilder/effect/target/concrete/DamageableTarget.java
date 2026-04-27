package io.bloogames.deckbuilder.effect.target.concrete;

import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.model.Damageable;

public abstract class DamageableTarget implements Target {
    private final Damageable damageable;

    public DamageableTarget(Damageable damageable) {
        this.damageable = damageable;
    }

    public Damageable damageable() {
        return damageable;
    }
}
