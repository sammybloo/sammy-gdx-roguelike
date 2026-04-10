package io.bloogames.deckbuilder.effect.execution;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.Effect;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.event.GameEvent;
import io.bloogames.deckbuilder.effect.step.EffectStep;
import io.bloogames.deckbuilder.effect.step.ReactionTiming;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.trigger.BattleTrigger;

public class EffectExecutor {
    private final Array<EffectExecution<?>> queue = new Array<>();
    private final Array<EffectExecution<?>> frontQueue = new Array<>();
    private final Array<BattleTrigger<?>> triggers = new Array<>();

    public void addTrigger(BattleTrigger<?> trigger) {
        triggers.add(trigger);
    }

    public void begin(Effect effect, TargetContext<? extends Target> context) {
        enqueueDeferred(effect, context);
    }

    public boolean hasPending() {
        return frontQueue.size > 0 || queue.size > 0;
    }

    public void update() {
        EffectExecution<?> execution;
        if (frontQueue.size > 0) {
            execution = frontQueue.removeIndex(frontQueue.size - 1);
        } else if (queue.size > 0) {
            execution = queue.removeIndex(0);
        } else {
            return;
        }

        runOneStep(execution);

        if (!execution.isDone()) {
            frontQueue.add(execution);
        }
    }

    private <T extends Target> void runOneStep(EffectExecution<T> execution) {
        if (!execution.hasSteps()) {
            return;
        }

        EffectStep step = execution.popStep();
        step.apply(execution.context(), this);
    }

    public void emit(GameEvent event, ReactionTiming timing) {
        for (int i = 0; i < triggers.size; i++) {
            dispatch(triggers.get(i), event, timing);
        }
    }

    private <E extends GameEvent> void dispatch(BattleTrigger<?> trigger, GameEvent event, ReactionTiming timing) {
        @SuppressWarnings("unchecked")
        BattleTrigger<E> typed = (BattleTrigger<E>) trigger;

        if (typed.timing() != timing) {
            return;
        }

        if (typed.eventType().isInstance(event)) {
            typed.onEvent(typed.eventType().cast(event), this);
        }
    }

    public void enqueueImmediate(Effect effect, TargetContext<? extends Target> context) {
        frontQueue.add(createExecution(effect, context));
    }

    public void enqueueDeferred(Effect effect, TargetContext<? extends Target> context) {
        queue.add(createExecution(effect, context));
    }

    private <T extends Target> EffectExecution<T> createExecution(Effect effect, TargetContext<T> context) {
        return new EffectExecution<>(context, effect.stepsFor(context.target().type()));
    }
}
