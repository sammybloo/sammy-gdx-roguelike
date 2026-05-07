package io.bloogames.deckbuilder.effect.step.concrete;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.target.concrete.BattlerTarget;
import io.bloogames.deckbuilder.model.stats.StatsModifier;

public class ChangeBattlerStatsStep implements TargetStep<BattlerTarget> {

    private final StatsModifier modifier;

    public ChangeBattlerStatsStep(StatsModifier modifier) {
        this.modifier = modifier;
    }

    @Override
    public void applyTarget(TargetContext<BattlerTarget> context) {
        context.target().battler().getStats().applyPermanentModifier(modifier);
    }
}
