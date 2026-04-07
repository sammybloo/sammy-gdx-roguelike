package io.bloogames.deckbuilder.effect.event;

public sealed interface BattleEvent permits DamageDealtEvent, BattlerAddedEvent {
}
