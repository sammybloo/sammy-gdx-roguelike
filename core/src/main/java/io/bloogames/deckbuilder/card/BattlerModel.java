package io.bloogames.deckbuilder.card;

import io.bloogames.deckbuilder.Stats;

public class BattlerModel {
    private BaseBattler base;
    private CardModel cardModel;
    private Stats stats;

    public BattlerModel(BaseBattler base) {
        this.cardModel = new CardModel(base.getBaseCard());
        stats = new Stats(base.getBaseStats());
        this.base = base;
    }

    public String getBattlerId() {
        return base.getId();
    }

    public int getPower() {
        return stats.getBaseStats().getPower();
    }

    public int getHealth() {
        return stats.getBaseStats().getHealth();
    }

    public CardModel getCardModel() {
        return cardModel;
    }
}
