package io.bloogames.deckbuilder.data;

import io.bloogames.deckbuilder.effect.TargetedEffect;
import io.bloogames.deckbuilder.effect.target.BaseTargetData;

public class BaseCard {

    private String cardId;
    private String cardName;
    private int cost;
    private TargetedEffect effect;

    public BaseCard(String cardId, String cardName, int cost, TargetedEffect effect) {
        this.cardId = cardId;
        this.cardName = cardName;
        this.cost = cost;
        this.effect = effect;
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
