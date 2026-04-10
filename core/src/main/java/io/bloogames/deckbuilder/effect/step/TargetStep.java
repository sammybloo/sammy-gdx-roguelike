package io.bloogames.deckbuilder.effect.step;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.effect.target.Target;

public interface TargetStep<T extends Target> extends EffectStep {
    void applyTarget(TargetContext<T> context, EffectExecutor executor);

    @Override
    @SuppressWarnings("unchecked")
    default void apply(TargetContext<? extends Target> context, EffectExecutor executor) {
        applyTarget((TargetContext<T>) context, executor);
    }
}
