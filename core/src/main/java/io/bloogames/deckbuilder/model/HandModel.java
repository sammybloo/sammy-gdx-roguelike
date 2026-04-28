package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
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
}
