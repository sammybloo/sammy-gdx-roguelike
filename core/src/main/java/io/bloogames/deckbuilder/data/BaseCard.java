package io.bloogames.deckbuilder.data;

import io.bloogames.deckbuilder.effect.TargetedEffect;
import io.bloogames.deckbuilder.effect.condition.SourceConditionList;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;

public abstract class BaseCard {

    private final String cardId;
    private final String cardName;
    private final int cost;
    private final TargetedEffect effect;
    private final SourceConditionList<? extends CardSource> conditionList;

    public BaseCard(String cardId, String cardName, int cost, TargetedEffect effect, SourceConditionList<? extends CardSource> conditionList) {
        this.cardId = cardId;
        this.cardName = cardName;
        this.cost = cost;
        this.effect = effect;
        this.conditionList = conditionList;
    }

    public String getCardId() {
        return cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public int getCost() {
        return cost;
    }

    public TargetedEffect getTargetedEffect() {
        return effect;
    }

    public SourceConditionList<? extends CardSource> getConditionList() {
        return conditionList;
    }
}
