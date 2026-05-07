package io.bloogames.deckbuilder.effect.step.concrete;

import io.bloogames.deckbuilder.effect.EffectBuilder;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.number.Amount;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.effect.target.concrete.BattlePartyTarget;

public class DrawCardsStep implements TargetStep<BattlePartyTarget> {
    private final Amount amount;

    public DrawCardsStep(Amount amount) {
        this.amount = amount;
    }

    @Override
    public void applyTarget(TargetContext<BattlePartyTarget> context) {
        EffectBuilder builder = new EffectBuilder();
        int n = amount.getCurrentInt();
        for (int i = 0; i < n; i++) {
            builder.addTargetStep(TargetType.BATTLE_PARTY, (modelProperties) -> new DrawCardStep());
        }
        context.game().getExecutor().enqueueImmediate(builder.build(), context);
    }
}
