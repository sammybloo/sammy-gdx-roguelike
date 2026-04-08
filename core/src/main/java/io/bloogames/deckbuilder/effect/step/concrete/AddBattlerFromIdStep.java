package io.bloogames.deckbuilder.effect.step.concrete;

import io.bloogames.deckbuilder.effect.EffectContext;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.target.concrete.SlotTarget;
import io.bloogames.deckbuilder.manager.CardManager;
import io.bloogames.deckbuilder.model.BattlerModel;

public class AddBattlerFromIdStep implements TargetStep<SlotTarget> {
    private String cardId;

    public AddBattlerFromIdStep(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public void applyTarget(EffectContext<SlotTarget> ctx, EffectExecutor executor) {
        AddBattlerStep battlerStep = new AddBattlerStep(new BattlerModel(CardManager.INSTANCE.getBattlerCard(cardId)));
        battlerStep.applyTarget(ctx, executor);
    }
}
