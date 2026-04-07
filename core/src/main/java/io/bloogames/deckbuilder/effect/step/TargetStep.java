package io.bloogames.deckbuilder.effect.step;

import io.bloogames.deckbuilder.effect.EffectContext;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.effect.target.Target;

@FunctionalInterface
public interface TargetStep<T extends Target> {
    void apply(EffectContext<T> ctx, EffectExecutor executor);
}
