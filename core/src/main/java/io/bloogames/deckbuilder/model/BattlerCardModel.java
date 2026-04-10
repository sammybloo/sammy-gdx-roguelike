package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.data.BaseBattlerCard;

public class BattlerCardModel extends CardModel {
    BaseBattlerCard baseBattlerCard;

    public BattlerCardModel(BaseBattlerCard base) {
        super(base);
        this.baseBattlerCard = base;
    }


    public int getPower() {
        return baseBattlerCard.getBaseStats().getPower();
    }

    public int getHealth() {
        return baseBattlerCard.getBaseStats().getHealth();
    }

    public BaseBattlerCard getBaseBattlerCard() {
        return baseBattlerCard;
    }
}
