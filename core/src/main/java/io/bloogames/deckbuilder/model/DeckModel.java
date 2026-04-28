package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.manager.SeedManager;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public final class DeckModel extends CardZone {
    public DeckModel(Ownership.Type owner) {
        super(owner);
    }

    public DeckModel(Array<CardModel> cards, Ownership.Type owner) {
        this(owner);
        for (CardModel card : cards) {
            addCard(card);
        }
    }

    public CardModel removeTopCard() {
        return getCards().pop();
    }

    public void shuffle() {
        SeedManager.INSTANCE.shuffle(getCards());
        for (CardModel card : getCards()) {
            card.setFaceup(false);
        }
    }
}
