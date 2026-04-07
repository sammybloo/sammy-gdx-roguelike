package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.effect.EffectContext;

public interface Damageable {
    int damage(EffectContext<?> context, int amount);

    int heal(EffectContext<?> context, int amount);
}
