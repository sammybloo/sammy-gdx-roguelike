package io.bloogames.deckbuilder.effect.target.concrete;

import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.Damageable;

public final class DamageableTarget implements Target {
    private final Damageable damageable;
    private final TargetType type;

    public DamageableTarget(Damageable damageable, TargetType type) {
        this.damageable = damageable;
        this.type = type;
    }

    @Override
    public TargetType type() {
        return type;
    }

    public Damageable damageable() {
        return damageable;
    }
}
