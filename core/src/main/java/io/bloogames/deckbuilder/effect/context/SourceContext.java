package io.bloogames.deckbuilder.effect.context;

import io.bloogames.deckbuilder.effect.source.EffectSource;
import io.bloogames.deckbuilder.model.BattleModel;

public record SourceContext<T extends EffectSource>(
    BattleModel battle,
    T source
) {
}
