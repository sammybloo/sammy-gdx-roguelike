package io.bloogames.deckbuilder.data;

public class BaseBattlerCard extends BaseCard {
    private BaseStats baseStats;

    public BaseBattlerCard(String cardId, String cardName, int cost, BaseStats baseStats) {
        super(cardId, cardName, cost);
        this.baseStats = baseStats;
    }

    public BaseStats getBaseStats() {
        return baseStats;
    }
}
