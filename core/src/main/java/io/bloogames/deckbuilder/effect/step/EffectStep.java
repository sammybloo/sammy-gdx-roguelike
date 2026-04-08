package io.bloogames.deckbuilder.effect.step;

import io.bloogames.deckbuilder.effect.EffectContext;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.effect.target.Target;

public interface EffectStep {
    void apply(EffectContext<? extends Target> context, EffectExecutor executor);
}
