package io.bloogames.deckbuilder.effect.execution;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.Effect;
import io.bloogames.deckbuilder.effect.EffectContext;
import io.bloogames.deckbuilder.effect.event.BattleEvent;
import io.bloogames.deckbuilder.effect.step.BattleStep;
import io.bloogames.deckbuilder.effect.step.ReactionTiming;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.trigger.BattleTrigger;

public class EffectExecutor {
    private final Array<EffectExecution<?>> queue = new Array<>();
    private final Array<EffectExecution<?>> frontQueue = new Array<>();
    private final Array<BattleTrigger<?>> triggers = new Array<>();

    public void addTrigger(BattleTrigger<?> trigger) {
        triggers.add(trigger);
    }

    public void begin(Effect effect, EffectContext<? extends Target> context) {
        queue.add(new EffectExecution<>(effect, context));
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

        if (!execution.battlePhaseDone() || execution.targetIndex() < execution.effect().targetSteps(execution.context().target().type()).size) {
            frontQueue.add(execution);
        }
    }

    private <T extends Target> void runOneStep(EffectExecution<T> execution) {
        Effect effect = execution.effect();
        EffectContext<T> ctx = execution.context();

        if (!execution.battlePhaseDone()) {
            Array<BattleStep> battleSteps = effect.battleSteps();

            if (execution.battleIndex() < battleSteps.size) {
                BattleStep step = battleSteps.get(execution.battleIndex());
                execution.battleIndex(execution.battleIndex() + 1);
                step.apply(ctx.battle(), ctx.source(), this);
                return;
            }

            execution.battlePhaseDone(true);
            execution.targetIndex(0);
        }

        Array<TargetStep<? extends Target>> steps = effect.targetSteps(ctx.target().type());

        if (execution.targetIndex() < steps.size) {
            @SuppressWarnings("unchecked")
            TargetStep<T> step = (TargetStep<T>) steps.get(execution.targetIndex());
            execution.targetIndex(execution.targetIndex() + 1);
            step.apply(ctx, this);
        }
    }

    public void emit(BattleEvent event, ReactionTiming timing) {
        for (int i = 0; i < triggers.size; i++) {
            dispatch(triggers.get(i), event, timing);
        }
    }

    private <E extends BattleEvent> void dispatch(BattleTrigger<?> trigger, BattleEvent event, ReactionTiming timing) {
        @SuppressWarnings("unchecked")
        BattleTrigger<E> typed = (BattleTrigger<E>) trigger;

        if (typed.timing() != timing) {
            return;
        }

        if (typed.eventType().isInstance(event)) {
            typed.onEvent(typed.eventType().cast(event), this);
        }
    }

    public void enqueueImmediate(Effect effect, EffectContext<? extends Target> context) {
        frontQueue.add(new EffectExecution<>(effect, context));
    }

    public void enqueueDeferred(Effect effect, EffectContext<? extends Target> context) {
        queue.add(new EffectExecution<>(effect, context));
    }
}
