package io.bloogames.deckbuilder.data;

import io.bloogames.deckbuilder.effect.target.TargetOwnerType;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.effect.target.BaseTargetData;

public class BaseBattlerCard extends BaseCard {
    private BaseStats baseStats;

    public BaseBattlerCard(String cardId, String cardName, int cost, BaseStats baseStats) {
        super(cardId, cardName, cost, new BaseTargetData(TargetType.SLOT, TargetOwnerType.OWN));
        this.baseStats = baseStats;
    }

    public BaseStats getBaseStats() {
        return baseStats;
    }
}
