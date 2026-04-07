package io.bloogames.deckbuilder.effect;

import io.bloogames.deckbuilder.effect.source.EffectSource;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.model.BattleModel;

public record EffectContext<T extends Target>(
    BattleModel battle,
    EffectSource source,
    T target
) {
}
