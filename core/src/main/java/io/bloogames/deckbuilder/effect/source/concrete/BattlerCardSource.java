package io.bloogames.deckbuilder.effect.source.concrete;

import io.bloogames.deckbuilder.model.BattlerCardModel;
import io.bloogames.deckbuilder.model.PartyModel;

public class BattlerCardSource extends CardSource {
    private BattlerCardModel battlerCard;

    public BattlerCardSource(BattlerCardModel card, PartyModel owner) {
        super(card, owner);
        this.battlerCard = card;
    }

    public BattlerCardModel battlerCard() {
        return battlerCard;
    }
}
