package io.bloogames.deckbuilder.effect.execution;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.Effect;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.step.EffectStep;
import io.bloogames.deckbuilder.effect.target.Target;

public class EffectExecutor {
    private final Array<EffectExecution<?>> queue = new Array<>();

    public void begin(Effect effect, TargetContext<? extends Target> context) {
        enqueueDeferred(effect, context);
    }

    public boolean hasPending() {
        return queue.size > 0;
    }

    public void update() {
        if (queue.size == 0) {
            return;
        }

        EffectExecution<?> execution = queue.removeIndex(queue.size - 1);
        runOneStep(execution);

        if (!execution.isDone()) {
            queue.add(execution);
        }
    }

    private <T extends Target> void runOneStep(EffectExecution<T> execution) {
        if (!execution.hasSteps()) {
            return;
        }

        EffectStep step = execution.popStep();
        step.apply(execution.context());
    }

    public void enqueueImmediate(Effect effect, TargetContext<? extends Target> context) {
        queue.add(createExecution(effect, context));
    }

    public void enqueueDeferred(Effect effect, TargetContext<? extends Target> context) {
        queue.insert(0, createExecution(effect, context));
    }

    private <T extends Target> EffectExecution<T> createExecution(Effect effect, TargetContext<T> context) {
        return new EffectExecution<>(context, effect.stepsFor(context.source().modelProperties(), context.target().types()));
    }
}
