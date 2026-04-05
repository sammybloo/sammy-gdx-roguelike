package io.bloogames.deckbuilder.data;

public class BaseBattler {
    private BaseBattlerCard baseCard;

    public BaseBattler(BaseBattlerCard baseCard) {
        this.baseCard = baseCard;
    }

    public String getId() {
        return baseCard.getCardId();
    }

    public String getBattlerName() {
        return baseCard.getCardName();
    }

    public BaseStats getBaseStats() {
        return baseCard.getBaseStats();
    }

    public BaseBattlerCard getBaseCard() {
        return baseCard;
    }
}
