package io.bloogames.deckbuilder.effect.context;

import io.bloogames.deckbuilder.effect.source.EffectSource;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.model.BattleModel;

public record TargetContext<T extends Target>(
    BattleModel battle,
    EffectSource source,
    T target
) {
}
