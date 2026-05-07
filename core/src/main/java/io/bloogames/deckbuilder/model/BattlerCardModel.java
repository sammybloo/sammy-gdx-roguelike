package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.data.BaseBattlerCard;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public class BattlerCardModel extends CardModel {
    private final BaseBattlerCard baseBattlerCard;

    public BattlerCardModel(BaseBattlerCard base, Ownership.Type owner) {
        super(base, owner);
        this.baseBattlerCard = base;
    }

    public int getPower() {
        return baseBattlerCard.getBaseStats().power();
    }

    public int getHealth() {
        return baseBattlerCard.getBaseStats().health();
    }

    public BaseBattlerCard getBaseBattlerCard() {
        return baseBattlerCard;
    }
}
