package io.bloogames.deckbuilder.effect.source.concrete;

import io.bloogames.deckbuilder.effect.source.Source;
import io.bloogames.deckbuilder.model.CardModel;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public class CardSource implements Source {
    private final CardModel card;

    public CardSource(CardModel card) {
        this.card = card;
    }

    // TODO: find a different ID for this that is actually unique
    @Override
    public String sourceId() {
        return card.getCardId();
    }

    @Override
    public Ownership.Type owner() {
        return card.getOwnership().getCurrentOwner();
    }

    public CardModel card() {
        return card;
    }
}
