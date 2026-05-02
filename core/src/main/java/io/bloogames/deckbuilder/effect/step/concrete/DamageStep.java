package io.bloogames.deckbuilder.effect.step.concrete;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.target.concrete.DamageableTarget;
import io.bloogames.deckbuilder.model.damage.Damage;

public class DamageStep implements TargetStep<DamageableTarget> {
    private final Damage damage;

    public DamageStep(Damage damage) {
        this.damage = damage;
    }

    @Override
    public void applyTarget(TargetContext<DamageableTarget> ctx) {
        ctx.game().getDamageCoordinator().damage(ctx.source(), ctx.target(), damage);
    }
}
