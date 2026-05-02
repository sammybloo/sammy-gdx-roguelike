package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.model.ownership.Ownership;

public final class HandModel extends CardZone {
    private final int maxSize;

    public HandModel(int maxSize, Ownership.Type owner) {
        super(owner);
        this.maxSize = maxSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public boolean isFull() {
        return getCards().size >= maxSize;
    }

    @Override
    public String toString() {
        return getOwner().toString() + " Hand";
    }
}
