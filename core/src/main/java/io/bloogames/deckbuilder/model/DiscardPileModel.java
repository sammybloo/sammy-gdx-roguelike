package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.model.ownership.Ownership;

public final class DiscardPileModel extends CardZone {
    public DiscardPileModel(Ownership.Type owner) {
        super(owner);
        setAddFaceUp(true);
    }
}
