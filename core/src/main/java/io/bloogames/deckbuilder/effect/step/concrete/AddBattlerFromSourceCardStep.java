package io.bloogames.deckbuilder.effect.step.concrete;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.effect.source.concrete.BattlerCardSource;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.target.concrete.SlotTarget;
import io.bloogames.deckbuilder.manager.CardManager;
import io.bloogames.deckbuilder.model.BattlerCardModel;
import io.bloogames.deckbuilder.model.BattlerModel;

public class AddBattlerFromSourceCardStep implements TargetStep<SlotTarget> {
    private String cardId;

    public AddBattlerFromSourceCardStep(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public void applyTarget(TargetContext<SlotTarget> ctx, EffectExecutor executor) {
        AddBattlerStep battlerStep = new AddBattlerStep(new BattlerModel(((BattlerCardSource) ctx.source()).battlerCard()));
        battlerStep.applyTarget(ctx, executor);
    }
}
