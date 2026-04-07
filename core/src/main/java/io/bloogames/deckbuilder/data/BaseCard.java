package io.bloogames.deckbuilder.data;

import io.bloogames.deckbuilder.effect.target.BaseTargetData;

public class BaseCard {

    private String cardId;
    private String cardName;
    private int cost;
    private BaseTargetData targetData;

    public BaseCard(String cardId, String cardName, int cost, BaseTargetData targetData) {
        this.cardId = cardId;
        this.cardName = cardName;
        this.cost = cost;
        this.targetData = targetData;
    }

    public String getCardId() {
        return cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public int getCost() {
        return cost;
    }

    public BaseTargetData getTargetData() {
        return targetData;
    }
}
