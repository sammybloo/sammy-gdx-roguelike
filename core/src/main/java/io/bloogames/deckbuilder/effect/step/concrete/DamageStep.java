package io.bloogames.deckbuilder.effect.step.concrete;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.effect.step.ReactionTiming;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.event.DamageDealtEvent;
import io.bloogames.deckbuilder.effect.target.concrete.DamageableTarget;

public class DamageStep implements TargetStep<DamageableTarget> {
    private final int amount;

    public DamageStep(int amount) {
        this.amount = amount;
    }

    @Override
    public void applyTarget(TargetContext<DamageableTarget> ctx, EffectExecutor executor) {
        ctx.target().damageable().damage(ctx, amount);

        executor.emit(
            new DamageDealtEvent(
                ctx.battle(), ctx.source(), ctx.target().damageable(), amount),
            ReactionTiming.IMMEDIATE
        );
    }
}
