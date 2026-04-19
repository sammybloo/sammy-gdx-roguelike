package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.data.BaseBattlerCard;
import io.bloogames.deckbuilder.effect.context.TargetContext;

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

    public int getMaxHealth() {
        return stats.getBaseStats().getHealth();
    }

    public int getCurrentHealth() {
        return getMaxHealth() - damage;
    }
    public CardModel getCardModel() {
        return cardModel;
    }

    @Override
    public int damage(TargetContext<?> context, int amount) {
        return damage += amount;
    }

    @Override
    public int heal(TargetContext<?> context, int amount) {
        return damage -= amount;
    }
}
