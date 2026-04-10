package io.bloogames.deckbuilder.effect.step;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.effect.target.Target;

public interface BattleStep extends EffectStep {
    @Override
    void apply(TargetContext<? extends Target> context, EffectExecutor executor);
}
