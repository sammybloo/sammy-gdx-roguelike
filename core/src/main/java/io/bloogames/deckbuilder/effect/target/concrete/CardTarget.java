package io.bloogames.deckbuilder.effect.target.concrete;

import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.CardModel;
import io.bloogames.deckbuilder.model.PartyModel;

public class CardTarget implements Target {

    private PartyModel owner;
    private CardModel card;

    public CardTarget(CardModel card, PartyModel owner) {
        this.owner = owner;
        this.card = card;
    }

    @Override
    public PartyModel owner() {
        return owner;
    }

    @Override
    public TargetType type() {
        return TargetType.CARD;
    }

    public CardModel card() {
        return card;
    }
}
