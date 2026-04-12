package io.bloogames.deckbuilder.effect.step.concrete;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.event.BattlerAddedEvent;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.target.concrete.SlotTarget;
import io.bloogames.deckbuilder.model.BattlerModel;

public class AddBattlerStep implements TargetStep<SlotTarget> {
    private BattlerModel battler;

    public AddBattlerStep(BattlerModel battler) {
        this.battler = battler;
    }

    @Override
    public void applyTarget(TargetContext<SlotTarget> ctx, EffectExecutor executor) {
        ctx.target().slot().setBattler(battler);
        executor.emit(
            new BattlerAddedEvent(
                ctx.battle(), ctx.source(), ctx.target().slot(), battler));
    }
}
