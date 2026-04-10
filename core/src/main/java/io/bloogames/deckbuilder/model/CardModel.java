package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.data.BaseCard;
import io.bloogames.deckbuilder.effect.condition.SourceConditionList;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.effect.target.TargetSpec;

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

    public BaseCard getBaseCard() {
        return base;
    }

    public boolean isFaceup() {
        return faceup;
    }

    public void setFaceup(boolean faceup) {
        this.faceup = faceup;
    }

    public TargetSpec getTargetSpec() {
        return base.getEffect().targetSpec();
    }
    public int getCurrentCost() {
        return base.getCost();
    }

    public SourceConditionList<? extends CardSource> getSourceConditionList() {
        return base.getConditionList();
    }
}
