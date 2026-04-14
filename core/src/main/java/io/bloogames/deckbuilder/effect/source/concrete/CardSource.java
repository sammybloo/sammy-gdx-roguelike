package io.bloogames.deckbuilder.effect.source.concrete;

import io.bloogames.deckbuilder.effect.source.Source;
import io.bloogames.deckbuilder.model.CardModel;
import io.bloogames.deckbuilder.model.BattlePartyModel;

public class CardSource implements Source {
    private final CardModel card;
    private final BattlePartyModel owner;

    public CardSource(CardModel card, BattlePartyModel owner) {
        this.card = card;
        this.owner = owner;
    }

    // TODO: find a different ID for this that is actually unique
    @Override
    public String sourceId() {
        return card.getCardId();
    }

    @Override
    public BattlePartyModel owner() {
        return owner;
    }

    public CardModel card() {
        return card;
    }
}
