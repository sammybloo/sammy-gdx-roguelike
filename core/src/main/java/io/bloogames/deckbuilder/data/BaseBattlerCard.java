package io.bloogames.deckbuilder.data;

import io.bloogames.deckbuilder.effect.EffectBuilder;
import io.bloogames.deckbuilder.effect.TargetedEffect;
import io.bloogames.deckbuilder.effect.condition.SourceConditionList;
import io.bloogames.deckbuilder.effect.condition.TargetConditionList;
import io.bloogames.deckbuilder.effect.condition.concrete.target.SlotIsEmpty;
import io.bloogames.deckbuilder.effect.source.concrete.BattlerCardSource;
import io.bloogames.deckbuilder.effect.step.concrete.AddBattlerFromIdStep;
import io.bloogames.deckbuilder.effect.step.concrete.AddBattlerFromSourceCardStep;
import io.bloogames.deckbuilder.effect.target.TargetOwnerType;
import io.bloogames.deckbuilder.effect.target.TargetSpec;
import io.bloogames.deckbuilder.effect.target.TargetType;

public class BaseBattlerCard extends BaseCard {
    private BaseStats baseStats;

    public BaseBattlerCard(String cardId, String cardName, int cost, BaseStats baseStats) {
        super(cardId, cardName, cost,
            new TargetedEffect(new TargetSpec(TargetOwnerType.OWN,
                TargetConditionList.builder().add(TargetType.SLOT, new SlotIsEmpty()).build(), TargetType.SLOT),
                new EffectBuilder().addTargetStep(TargetType.SLOT, new AddBattlerFromSourceCardStep(cardId)).build()),
            SourceConditionList.none());
        this.baseStats = baseStats;
    }
    public BaseStats getBaseStats() {
        return baseStats;
    }
}
