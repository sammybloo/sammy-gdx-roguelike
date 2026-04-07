package io.bloogames.deckbuilder.effect.event.concrete;

import io.bloogames.deckbuilder.effect.source.EffectSource;
import io.bloogames.deckbuilder.effect.event.BattleEvent;
import io.bloogames.deckbuilder.model.BattleModel;
import io.bloogames.deckbuilder.model.Damageable;

public record DamageDealtEvent(
    BattleModel battle,
    EffectSource source,
    Damageable target,
    int amount
) implements BattleEvent {
}
