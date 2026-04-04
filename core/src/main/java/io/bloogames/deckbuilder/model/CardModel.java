package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.data.BaseCard;

public class CardModel {
    private BaseCard base;
    private boolean faceup = true;

    public CardModel(BaseCard base) {
        this.base = base;
    }

    public String getCardId() {
        return base.getCardId();
    }

    public String getCardName() {
        return base.getCardName();
    }

    public boolean isFaceup() {
        return faceup;
    }

    public void setFaceup(boolean faceup) {
        this.faceup = faceup;
    }
}
