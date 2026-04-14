package io.bloogames.deckbuilder.effect.context;

import io.bloogames.deckbuilder.effect.source.Source;
import io.bloogames.deckbuilder.model.GameModel;

public record SourceContext<T extends Source>(
    GameModel game,
    T source
) {
}
