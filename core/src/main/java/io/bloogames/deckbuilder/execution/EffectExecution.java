package io.bloogames.deckbuilder.execution;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.step.EffectStep;
import io.bloogames.deckbuilder.effect.target.Target;

public final class EffectExecution<T extends Target> {
    private final TargetContext<T> context;
    private final Array<EffectStep> steps;

    public EffectExecution(TargetContext<T> context, Array<EffectStep> steps) {
        this.context = context;
        this.steps = new Array<>(steps);
    }

    public TargetContext<T> context() {
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
