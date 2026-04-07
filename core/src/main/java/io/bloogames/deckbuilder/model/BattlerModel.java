package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.data.BaseBattler;
import io.bloogames.deckbuilder.effect.EffectContext;

public class BattlerModel implements Damageable {
    private BaseBattler base;
    private CardModel cardModel;
    private Stats stats;
    private int damage;

    public BattlerModel(BaseBattler base) {
        this.cardModel = new CardModel(base.getBaseCard());
        stats = new Stats(base.getBaseStats());
        this.base = base;
    }

    public String getBattlerId() {
        return base.getId();
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
