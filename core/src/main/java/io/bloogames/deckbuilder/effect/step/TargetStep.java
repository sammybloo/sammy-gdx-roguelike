package io.bloogames.deckbuilder.effect.step;

import io.bloogames.deckbuilder.effect.EffectContext;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;

public interface TargetStep<T extends Target> extends EffectStep {
    void applyTarget(EffectContext<T> context, EffectExecutor executor);

    @Override
    @SuppressWarnings("unchecked")
    default void apply(EffectContext<? extends Target> context, EffectExecutor executor) {
        applyTarget((EffectContext<T>) context, executor);
    }
}
