package io.bloogames.deckbuilder.data;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.TargetedEffect;
import io.bloogames.deckbuilder.effect.condition.SourceConditionList;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.model.aura.AuraModel;

public class BaseActionCard extends BaseCard {
    public BaseActionCard(String cardId, int cost, TargetedEffect effect, SourceConditionList<? extends CardSource> conditionList, Array<AuraModel> auras, BaseProperties properties) {
        super(cardId, cost, effect, conditionList, auras, properties);
    }
}
