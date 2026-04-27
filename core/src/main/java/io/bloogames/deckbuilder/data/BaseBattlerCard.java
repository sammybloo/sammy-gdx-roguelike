package io.bloogames.deckbuilder.data;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.EffectBuilder;
import io.bloogames.deckbuilder.effect.TargetedEffect;
import io.bloogames.deckbuilder.effect.condition.SourceConditionList;
import io.bloogames.deckbuilder.effect.condition.TargetConditionList;
import io.bloogames.deckbuilder.effect.condition.concrete.target.SlotIsEmpty;
import io.bloogames.deckbuilder.effect.step.concrete.AddBattlerFromSourceCardStep;
import io.bloogames.deckbuilder.effect.target.TargetOwnerType;
import io.bloogames.deckbuilder.effect.target.TargetSpec;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.aura.Aura;

public class BaseBattlerCard extends BaseCard {
    private final BaseStats baseStats;
    private final AuraSupplier auraSupplier;

    public BaseBattlerCard(String cardId, String cardName, int cost, BaseStats baseStats, Array<Aura> auras) {
        super(cardId, cardName, cost,
            new TargetedEffect(new TargetSpec(TargetOwnerType.OWN,
                TargetConditionList.builder().add(TargetType.SLOT, new SlotIsEmpty()).build(), TargetType.SLOT),
                new EffectBuilder().addTargetStep(TargetType.SLOT, new AddBattlerFromSourceCardStep(cardId)).build()),
            SourceConditionList.none());
        this.baseStats = baseStats;
        this.auraSupplier = new AuraSupplier(auras);
    }

    public BaseStats getBaseStats() {
        return baseStats;
    }

    public Array<Aura> getAuras() {
        return auraSupplier.get();
    }
}
