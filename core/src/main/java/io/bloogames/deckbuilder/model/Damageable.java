package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.effect.context.TargetContext;

public interface Damageable {
    int damage(TargetContext<?> context, int amount);

    int heal(TargetContext<?> context, int amount);
}
