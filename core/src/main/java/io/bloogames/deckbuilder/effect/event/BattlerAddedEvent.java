package io.bloogames.deckbuilder.effect.event;

import io.bloogames.deckbuilder.effect.source.EffectSource;
import io.bloogames.deckbuilder.model.*;

public record BattlerAddedEvent(
    BattleModel battle,
    EffectSource source,
    SlotModel slot,
    BattlerModel battler
) implements GameEvent {
}
