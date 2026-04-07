package io.bloogames.deckbuilder.effect.step.concrete;

import io.bloogames.deckbuilder.data.BaseBattlerCard;
import io.bloogames.deckbuilder.effect.EffectContext;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.target.concrete.SlotTarget;
import io.bloogames.deckbuilder.manager.CardManager;
import io.bloogames.deckbuilder.model.BattlerModel;

import java.util.function.Supplier;

public class AddBattlerFromIdStep implements TargetStep<SlotTarget> {
    private String cardId;

    public AddBattlerFromIdStep(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public void apply(EffectContext<SlotTarget> ctx, EffectExecutor executor) {
        AddBattlerStep battlerStep = new AddBattlerStep(new BattlerModel(CardManager.INSTANCE.getBattlerCard(cardId)));
        battlerStep.apply(ctx, executor);
    }
}
