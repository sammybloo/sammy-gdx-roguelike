package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public class DiscardPileModel {
    private Ownership ownership;
    private Array<CardModel> cards;

    public DiscardPileModel(Ownership.Type owner) {
        this.ownership = new Ownership(owner);
        cards = new Array<>();
    }

    public Ownership getOwnership() {
        return ownership;
    }

    public void addCard(CardModel card) {
        cards.add(card);
    }

    public int size() {
        return cards.size;
    }

    public Array<CardModel> getCards() {
        return cards;
    }

    public void removeCard(CardModel card) {
        cards.removeValue(card, true);
    }
}
