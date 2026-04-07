package io.bloogames.deckbuilder.effect.event;

import io.bloogames.deckbuilder.effect.event.concrete.DamageDealtEvent;

public sealed interface BattleEvent permits DamageDealtEvent {
}
