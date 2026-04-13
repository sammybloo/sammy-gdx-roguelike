package io.bloogames.deckbuilder.effect.step.concrete;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.target.concrete.SlotTarget;
import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.model.BattlerModel;

public class AddBattlerStep implements TargetStep<SlotTarget> {
    private BattlerModel battler;

    public AddBattlerStep(BattlerModel battler) {
        this.battler = battler;
    }

    @Override
    public void applyTarget(TargetContext<SlotTarget> context) {
        context.target().slot().setBattler(battler);
        context.battle().dispatch(
            new GameEvent.BattlerAddedEvent(context.target().slot(), battler));
    }
}
