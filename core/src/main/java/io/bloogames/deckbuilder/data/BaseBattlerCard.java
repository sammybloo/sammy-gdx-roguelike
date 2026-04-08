package io.bloogames.deckbuilder.data;

import io.bloogames.deckbuilder.effect.EffectBuilder;
import io.bloogames.deckbuilder.effect.TargetedEffect;
import io.bloogames.deckbuilder.effect.step.concrete.AddBattlerFromIdStep;
import io.bloogames.deckbuilder.effect.target.TargetOwnerType;
import io.bloogames.deckbuilder.effect.target.TargetSpec;
import io.bloogames.deckbuilder.effect.target.TargetType;

public class BaseBattlerCard extends BaseCard {
    private BaseStats baseStats;

    public BaseBattlerCard(String cardId, String cardName, int cost, BaseStats baseStats) {
        super(cardId, cardName, cost,
            new TargetedEffect(new TargetSpec(TargetOwnerType.OWN, TargetType.SLOT),
                new EffectBuilder().addTargetStep(TargetType.SLOT, new AddBattlerFromIdStep(cardId)).build()));
        this.baseStats = baseStats;
    }
    public BaseStats getBaseStats() {
        return baseStats;
    }
}
