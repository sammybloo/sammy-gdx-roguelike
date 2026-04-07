package io.bloogames.deckbuilder.effect.step;

import io.bloogames.deckbuilder.effect.source.EffectSource;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.model.BattleModel;

@FunctionalInterface
public interface BattleStep {
    void apply(BattleModel battle, EffectSource source, EffectExecutor executor);
}
