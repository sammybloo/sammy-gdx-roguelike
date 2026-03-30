package io.bloogames.deckbuilder.card;

public class CardModel {
    private BaseCard base;

    public CardModel(BaseCard base) {
        this.base = base;
    }

    public String getCardId() {
        return base.getCardId();
    }

    public String getCardName() {
        return base.getCardName();
    }
}
