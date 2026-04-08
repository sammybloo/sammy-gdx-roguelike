package io.bloogames.deckbuilder.effect.execution;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.EffectContext;
import io.bloogames.deckbuilder.effect.step.EffectStep;
import io.bloogames.deckbuilder.effect.target.Target;

public final class EffectExecution<T extends Target> {
    private final EffectContext<T> context;
    private final Array<EffectStep> steps;

    public EffectExecution(EffectContext<T> context, Array<EffectStep> steps) {
        this.context = context;
        this.steps = new Array<>(steps);
    }

    public EffectContext<T> context() {
        return context;
    }

    public boolean hasSteps() {
        return steps.size > 0;
    }

    public EffectStep popStep() {
        return steps.removeIndex(0);
    }

    public boolean isDone() {
        return steps.size == 0;
    }
}
