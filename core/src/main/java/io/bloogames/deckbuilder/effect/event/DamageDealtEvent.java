package io.bloogames.deckbuilder.effect.event;

import io.bloogames.deckbuilder.effect.source.Source;
import io.bloogames.deckbuilder.model.BattleModel;
import io.bloogames.deckbuilder.model.Damageable;

public record DamageDealtEvent(
    BattleModel battle,
    Source source,
    Damageable target,
    int amount
) implements GameEvent {
}
