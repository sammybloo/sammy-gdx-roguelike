package io.bloogames.deckbuilder.effect.event;

import io.bloogames.deckbuilder.effect.source.Source;
import io.bloogames.deckbuilder.model.BattleModel;
import io.bloogames.deckbuilder.model.BattlerModel;
import io.bloogames.deckbuilder.model.SlotModel;

public record BattlerAddedEvent(
    BattleModel battle,
    Source source,
    SlotModel slot,
    BattlerModel battler
) implements GameEvent {
}
