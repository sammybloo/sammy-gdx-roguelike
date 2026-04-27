package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.data.BaseCard;
import io.bloogames.deckbuilder.effect.Effect;
import io.bloogames.deckbuilder.effect.condition.SourceConditionList;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.effect.target.TargetSpec;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public abstract class CardModel {
    private BaseCard base;
    private Ownership ownership;
    private boolean faceup = false;

    public CardModel(BaseCard base, Ownership.Type owner) {
        this.base = base;
        this.ownership = new Ownership(owner);
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
        return base.getTargetedEffect().targetSpec();
    }

    public Effect getEffect() {
        return base.getTargetedEffect().effect();
    }

    public int getCurrentCost() {
        return base.getCost();
    }

    public Ownership getOwnership() {
        return ownership;
    }

    public SourceConditionList<? extends CardSource> getSourceConditionList() {
        return base.getConditionList();
    }
}
