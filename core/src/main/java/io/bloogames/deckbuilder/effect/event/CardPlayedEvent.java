package io.bloogames.deckbuilder.effect.event;

import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.model.BattleModel;
import io.bloogames.deckbuilder.model.CardModel;

public record CardPlayedEvent (
    BattleModel battle,
    CardModel card,
    CardSource cardSource,
    Target target
) implements GameEvent {}
