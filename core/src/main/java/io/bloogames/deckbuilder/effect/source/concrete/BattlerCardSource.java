package io.bloogames.deckbuilder.effect.source.concrete;

import io.bloogames.deckbuilder.model.BattlerCardModel;
import io.bloogames.deckbuilder.model.CardModel;
import io.bloogames.deckbuilder.model.LeaderModel;

public class BattlerCardSource extends CardSource {
    private BattlerCardModel battlerCard;

    public BattlerCardSource(BattlerCardModel card, LeaderModel caster) {
        super(card, caster);
        this.battlerCard = card;
    }

    public BattlerCardModel battlerCard() {
        return battlerCard;
    }
}
