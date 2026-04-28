package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public abstract class CardZone {
    private final Array<CardModel> cards = new Array<>();
    private boolean addFaceUp = false;
    private final Ownership.Type owner;

    public CardZone(Ownership.Type owner) {
        this.owner = owner;
    }

    public boolean addCard(CardModel card) {
        if (isFull() || contains(card)) return false;
        cards.add(card);
        if (addFaceUp) {
            card.setFaceup(true);
        }
        card.getOwnership().setCurrentOwner(owner);
        return true;
    }

    public boolean removeCard(CardModel card) {
        return cards.removeValue(card, true);
    }

    public boolean contains(CardModel card) {
        return cards.contains(card, true);
    }

    public boolean isFull() {
        return false;
    }

    public Array<CardModel> getCards() {
        return cards;
    }

    public int indexOf(CardModel card) {
        return cards.indexOf(card, true);
    }

    public int size() {
        return cards.size;
    }

    public Ownership.Type getOwner() {
        return owner;
    }

    public void setAddFaceUp(boolean addFaceUp) {
        this.addFaceUp = addFaceUp;
    }

    public enum Type {
        HAND,
        DECK,
        DISCARD_PILE
    }
}
