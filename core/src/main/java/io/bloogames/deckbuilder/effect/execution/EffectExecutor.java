package io.bloogames.deckbuilder.effect.execution;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.Effect;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.event.GameEvent;
import io.bloogames.deckbuilder.effect.event.GameEventDispatcher;
import io.bloogames.deckbuilder.effect.step.EffectStep;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.trigger.GameEventTrigger;

public class EffectExecutor {
    private final Array<EffectExecution<?>> queue = new Array<>();
    private final GameEventDispatcher eventDispatcher;

    public EffectExecutor(GameEventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    public void addSubscriber(GameEventTrigger<?> subscriber) {
        eventDispatcher.addSubscriber(subscriber);
    }

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
        step.apply(execution.context(), this);
    }

    public void emit(GameEvent event) {
        eventDispatcher.emit(event, this);
    }

    public void enqueueImmediate(Effect effect, TargetContext<? extends Target> context) {
        queue.add(createExecution(effect, context));
    }

    public void enqueueDeferred(Effect effect, TargetContext<? extends Target> context) {
        queue.insert(0, createExecution(effect, context));
    }

    private <T extends Target> EffectExecution<T> createExecution(Effect effect, TargetContext<T> context) {
        return new EffectExecution<>(context, effect.stepsFor(context.target().type()));
    }
}
