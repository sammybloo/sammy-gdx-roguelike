package io.bloogames.deckbuilder.data;

import io.bloogames.deckbuilder.effect.TargetedEffect;
import io.bloogames.deckbuilder.effect.condition.SourceConditionList;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;

public class BaseActionCard extends BaseCard {
    public BaseActionCard(String cardId, String cardName, int cost, TargetedEffect effect, SourceConditionList<? extends CardSource> conditionList) {
        super(cardId, cardName, cost, effect, conditionList);
    }
}
