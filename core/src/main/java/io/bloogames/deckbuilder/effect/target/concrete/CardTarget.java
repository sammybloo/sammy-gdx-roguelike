package io.bloogames.deckbuilder.effect.target.concrete;

import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.BattlePartyModel;
import io.bloogames.deckbuilder.model.CardModel;

public class CardTarget implements Target {

    private BattlePartyModel owner;
    private CardModel card;

    public CardTarget(CardModel card, BattlePartyModel owner) {
        this.owner = owner;
        this.card = card;
    }

    @Override
    public BattlePartyModel owner() {
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
