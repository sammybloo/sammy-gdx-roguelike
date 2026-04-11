package io.bloogames.deckbuilder.effect.event;

import io.bloogames.deckbuilder.effect.source.Source;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.error.ValidationError;
import io.bloogames.deckbuilder.model.BattleModel;
import io.bloogames.deckbuilder.model.CardModel;

public record CardFailedEvent (
    BattleModel battle,
    CardModel card,
    Target target,
    ValidationError error
) implements GameEvent {}
