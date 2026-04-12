package io.bloogames.deckbuilder.effect.event;

import io.bloogames.deckbuilder.model.BattleModel;
import io.bloogames.deckbuilder.ui.BattleState;

public record BattleStateEvent (
    BattleModel battle,
    BattleState oldState,
    BattleState newState
) implements GameEvent {
}
