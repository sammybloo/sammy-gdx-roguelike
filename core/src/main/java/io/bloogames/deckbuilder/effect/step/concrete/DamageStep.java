package io.bloogames.deckbuilder.effect.step.concrete;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.target.concrete.DamageableTarget;
import io.bloogames.deckbuilder.event.GameEvent;

public class DamageStep implements TargetStep<DamageableTarget> {
    private final int amount;

    public DamageStep(int amount) {
        this.amount = amount;
    }

    @Override
    public void applyTarget(TargetContext<DamageableTarget> ctx) {
        ctx.target().damageable().damage(ctx, amount);

        ctx.battle().dispatch(
            new GameEvent.DamageDealtEvent(
                ctx.battle(), ctx.source(), ctx.target().damageable(), amount));
    }
}
