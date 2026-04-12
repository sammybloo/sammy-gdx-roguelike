package io.bloogames.deckbuilder.effect.event;

public sealed interface GameEvent
    permits DamageDealtEvent, BattlerAddedEvent, CardPlayedEvent, CardFailedEvent, BattleStateEvent {
}
