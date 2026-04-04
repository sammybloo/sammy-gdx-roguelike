package io.bloogames.deckbuilder.data;

public class BaseBattler {
    private BaseCard baseCard;
    private BaseStats baseStats;

    public BaseBattler(BaseCard baseCard, BaseStats baseStats) {
        this.baseCard = baseCard;
        this.baseStats = baseStats;
    }

    public String getId() {
        return baseCard.getCardId();
    }

    public String getBattlerName() {
        return baseCard.getCardName();
    }

    public BaseStats getBaseStats() {
        return baseStats;
    }

    public BaseCard getBaseCard() {
        return baseCard;
    }
}
