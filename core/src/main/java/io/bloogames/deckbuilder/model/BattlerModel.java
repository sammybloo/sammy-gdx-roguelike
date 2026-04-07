package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.data.BaseBattlerCard;
import io.bloogames.deckbuilder.effect.EffectContext;

public class BattlerModel implements Damageable {
    private CardModel cardModel;
    private Stats stats;
    private int damage;

    public BattlerModel(BaseBattlerCard cardModel) {
        this.cardModel = new BattlerCardModel(cardModel);
        this.stats = new Stats(cardModel.getBaseStats());
    }

    public BattlerModel(BattlerCardModel cardModel) {
        this.cardModel = cardModel;
        this.stats = new Stats(cardModel.getBaseBattlerCard().getBaseStats());
    }

    public String getBattlerId() {
        return cardModel.getCardId();
    }

    public int getPower() {
        return stats.getBaseStats().getPower();
    }

    public int getHealth() {
        return stats.getBaseStats().getHealth();
    }

    public CardModel getCardModel() {
        return cardModel;
    }

    @Override
    public int damage(EffectContext<?> context, int amount) {
        return damage += amount;
    }

    @Override
    public int heal(EffectContext<?> context, int amount) {
        return damage -= amount;
    }
}
