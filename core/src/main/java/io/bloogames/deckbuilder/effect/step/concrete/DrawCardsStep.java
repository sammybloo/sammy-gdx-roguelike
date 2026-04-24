package io.bloogames.deckbuilder.effect.step.concrete;

import io.bloogames.deckbuilder.effect.EffectBuilder;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.effect.target.concrete.BattlePartyTarget;

public class DrawCardsStep implements TargetStep<BattlePartyTarget> {
    private final int amount;

    public DrawCardsStep(int amount) {
        this.amount = amount;
    }

    @Override
    public void applyTarget(TargetContext<BattlePartyTarget> context) {
        EffectBuilder builder = new EffectBuilder();
        for (int i = 0; i < amount; i++) {
            builder.addTargetStep(TargetType.BATTLE_PARTY, new DrawCardStep());
        }
        context.game().getExecutor().enqueueImmediate(builder.build(), context);
    }
}
