package io.bloogames.deckbuilder.effect.context;

import io.bloogames.deckbuilder.effect.source.Source;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.model.GameModel;

public record TargetContext<T extends Target>(
    GameModel game,
    Source source,
    T target
) {
}
