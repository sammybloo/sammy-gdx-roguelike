package io.bloogames.deckbuilder.data;

public class BaseCard {

    private String cardId;
    private String cardName;
    private int cost;

    public BaseCard(String cardId, String cardName, int cost) {
        this.cardId = cardId;
        this.cardName = cardName;
        this.cost = cost;
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
}
