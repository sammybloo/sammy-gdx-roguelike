package io.bloogames.deckbuilder.data;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.TargetedEffect;
import io.bloogames.deckbuilder.effect.condition.SourceConditionList;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.model.aura.AuraModel;

public abstract class BaseCard {

    private final String cardId;
    private final int cost;
    private final TargetedEffect effect;
    private final AuraSupplier auraSupplier;
    private final SourceConditionList<? extends CardSource> conditionList;
    private final BaseProperties properties;

    public BaseCard(String cardId, int cost, TargetedEffect effect,
                    SourceConditionList<? extends CardSource> conditionList, Array<AuraModel> auras, BaseProperties properties) {
        this.cardId = cardId;
        this.cost = cost;
        this.effect = effect;
        this.conditionList = conditionList;
        this.auraSupplier = new AuraSupplier(auras);
        this.properties = properties;
    }

    public String getCardId() {
        return cardId;
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

    public Array<AuraModel> getAuras() {
        return auraSupplier.get();
    }

    public BaseProperties getProperties() {
        return properties;
    }
}
