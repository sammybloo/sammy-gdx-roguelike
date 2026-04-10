package io.bloogames.deckbuilder.effect.context;

import io.bloogames.deckbuilder.effect.source.EffectSource;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.model.BattleModel;
import io.bloogames.deckbuilder.model.PartyModel;

public record TargetContext<T extends Target>(
    BattleModel battle,
    EffectSource source,
    T target
) {
}
