package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public class HandModel {

    private final Array<CardModel> cards = new Array<>();
    private final int maxSize;
    private boolean drawFaceUp = false;
    private Ownership ownership;

    public HandModel(int maxSize, Ownership.Type owner) {
        this.maxSize = maxSize;
        this.ownership = new Ownership(owner);
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int size() {
        return cards.size;
    }

    public boolean isFull() {
        return cards.size >= maxSize;
    }

    public boolean contains(CardModel card) {
        return cards.contains(card, true);
    }

    public int indexOf(CardModel card) {
        return cards.indexOf(card, true);
    }

    public Array<CardModel> getCards() {
        return cards;
    }

    public Ownership getOwnership() {
        return ownership;
    }

    public boolean addCard(CardModel card) {
        if (isFull() || contains(card)) return false;
        cards.add(card);
        if (drawFaceUp) {
            card.setFaceup(true);
        }
        return true;
    }

    public boolean removeCard(CardModel card) {
        return cards.removeValue(card, true);
    }

    public boolean moveCard(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex >= cards.size) return false;
        if (toIndex < 0 || toIndex >= cards.size) return false;
        if (fromIndex == toIndex) return true;

        CardModel card = cards.removeIndex(fromIndex);
        cards.insert(toIndex, card);
        return true;
    }

    public void setDrawFaceUp(boolean drawFaceUp) {
        this.drawFaceUp = drawFaceUp;
    }
}
