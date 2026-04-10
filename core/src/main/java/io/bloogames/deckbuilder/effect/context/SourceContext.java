package io.bloogames.deckbuilder.effect.context;

import io.bloogames.deckbuilder.effect.source.Source;
import io.bloogames.deckbuilder.model.BattleModel;

public record SourceContext<T extends Source>(
    BattleModel battle,
    T source
) {
}
