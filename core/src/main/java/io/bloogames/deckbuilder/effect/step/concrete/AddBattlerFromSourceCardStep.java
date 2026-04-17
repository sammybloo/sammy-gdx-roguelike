package io.bloogames.deckbuilder.effect.step.concrete;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.target.concrete.SlotTarget;
import io.bloogames.deckbuilder.model.BattlerCardModel;
import io.bloogames.deckbuilder.model.BattlerModel;

public class AddBattlerFromSourceCardStep implements TargetStep<SlotTarget> {
    private String cardId;

    public AddBattlerFromSourceCardStep(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public void applyTarget(TargetContext<SlotTarget> ctx) {

        AddBattlerStep battlerStep = new AddBattlerStep(new BattlerModel((BattlerCardModel) (((CardSource) ctx.source()).card())));
        battlerStep.applyTarget(ctx);
    }
}
