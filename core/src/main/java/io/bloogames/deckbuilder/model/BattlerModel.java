package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.data.BaseBattlerCard;
import io.bloogames.deckbuilder.effect.context.TargetContext;

public class BattlerModel implements Damageable {
    private BattlerCardModel cardModel;
    private StatsModel stats;
    private int damage;
    private Array<Aura> auras;

    public BattlerModel(BaseBattlerCard cardModel) {
        this(new BattlerCardModel(cardModel));
    }

    public BattlerModel(BattlerCardModel cardModel) {
        this.cardModel = cardModel;
        this.stats = new StatsModel(cardModel.getBaseBattlerCard().getBaseStats());
        this.auras = new Array<>();
    }

    public String getBattlerId() {
        return cardModel.getCardId();
    }

    public int getPower() {
        return stats.getBaseStats().power();
    }

    public int getMaxHealth() {
        return stats.getBaseStats().health();
    }

    public int getCurrentHealth() {
        return getMaxHealth() - damage;
    }

    public BattlerCardModel getCardModel() {
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

    public void addAllAuras(Array<Aura> arr) {
        arr.addAll(auras);
        cardModel.addAllAuras(arr);
    }
}
