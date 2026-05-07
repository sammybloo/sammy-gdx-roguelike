package io.bloogames.deckbuilder.card;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.data.BaseActionCard;
import io.bloogames.deckbuilder.data.BaseBattlerCard;
import io.bloogames.deckbuilder.data.BaseProperties;
import io.bloogames.deckbuilder.data.BaseStats;
import io.bloogames.deckbuilder.effect.TargetedEffect;
import io.bloogames.deckbuilder.effect.condition.SourceConditionList;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.model.aura.AuraModel;

public class CardBuilder {
    private final Array<AuraModel> auras = new Array<>();
    private String cardId;
    private BaseProperties properties;
    private Integer cost;
    private TargetedEffect effect;
    private SourceConditionList<? extends CardSource> conditionList = SourceConditionList.none();
    private BaseStats baseStats;

    public static CardBuilder create() {
        return new CardBuilder();
    }

    public CardBuilder id(String cardId) {
        requireUnset(this.cardId, "Card id");
        this.cardId = cardId;
        return this;
    }

    public CardBuilder properties(BaseProperties properties) {
        requireUnset(this.properties, "Base properties");
        this.properties = properties;
        return this;
    }

    public CardBuilder cost(int cost) {
        requireUnset(this.cost, "Card cost");
        this.cost = cost;
        return this;
    }

    public CardBuilder effect(TargetedEffect effect) {
        requireUnset(this.effect, "Targeted effect");
        this.effect = effect;
        return this;
    }

    public CardBuilder conditions(SourceConditionList<? extends CardSource> conditionList) {
        if (!this.conditionList.equals(SourceConditionList.none())) {
            throw new IllegalStateException("Source conditions have already been set.");
        }

        this.conditionList = conditionList;
        return this;
    }

    public CardBuilder stats(int power, int health) {
        requireUnset(baseStats, "Base stats");
        this.baseStats = new BaseStats(power, health);
        return this;
    }

    public CardBuilder aura(AuraModel aura) {
        auras.add(aura);
        return this;
    }

    public BaseActionCard buildAction() {
        validateBaseFields();

        if (effect == null) {
            throw new IllegalStateException("Action cards require a targeted effect.");
        }

        if (properties == null) {
            properties = BaseProperties.EMPTY;
        }

        return new BaseActionCard(cardId, cost, effect, conditionList, auras, properties);
    }

    public BaseBattlerCard buildBattler() {
        validateBaseFields();

        if (baseStats == null) {
            throw new IllegalStateException("Battler cards require base stats.");
        }

        if (properties == null) {
            properties = BaseProperties.EMPTY;
        }

        return new BaseBattlerCard(cardId, cost, baseStats, auras, properties);
    }

    private void validateBaseFields() {
        if (cardId == null || cardId.isBlank()) {
            throw new IllegalStateException("Card id is required.");
        }

        if (cost == null) {
            throw new IllegalStateException("Card cost is required.");
        }

        if (cost < 0) {
            throw new IllegalStateException("Card cost cannot be negative.");
        }
    }

    private void requireUnset(Object value, String propertyName) {
        if (value != null) {
            throw new IllegalStateException(propertyName + " has already been set.");
        }
    }
}
