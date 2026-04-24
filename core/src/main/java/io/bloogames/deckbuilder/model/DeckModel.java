package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.manager.SeedManager;

public class DeckModel {
    private final Array<CardModel> cards = new Array<>();

    public DeckModel() {
    }

    public DeckModel(Array<CardModel> cards) {
        this.cards.addAll(cards);
    }

    public void addCard(CardModel card) {
        cards.add(card);
    }

    public boolean removeCard(CardModel card) {
        return cards.removeValue(card, true);
    }

    public Array<CardModel> getCards() {
        return cards;
    }

    public CardModel removeTopCard() {
        return cards.pop();
    }

    public void shuffle() {
        SeedManager.INSTANCE.shuffle(cards);
        for (CardModel card : cards) {
            card.setFaceup(false);
        }
    }
}
