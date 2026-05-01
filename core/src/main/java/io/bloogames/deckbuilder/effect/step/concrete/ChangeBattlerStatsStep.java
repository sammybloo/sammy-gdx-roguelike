package io.bloogames.deckbuilder.effect.step.concrete;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.target.concrete.BattlerTarget;
import io.bloogames.deckbuilder.model.stats.StatChanges;

public class ChangeBattlerStatsStep implements TargetStep<BattlerTarget> {

    private final StatChanges changes;

    public ChangeBattlerStatsStep(StatChanges changes) {
        this.changes = changes;
    }

    @Override
    public void applyTarget(TargetContext<BattlerTarget> context) {
        context.target().battler().getStats().permanentlyChange(changes);
    }
}
