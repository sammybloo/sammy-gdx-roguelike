package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.data.AuraSupplier;
import io.bloogames.deckbuilder.data.BaseBattlerCard;
import io.bloogames.deckbuilder.model.aura.Aura;
import io.bloogames.deckbuilder.model.aura.AuraSet;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public class BattlerModel implements Damageable {
    private final BattlerCardModel cardModel;
    private final StatsModel stats;
    private int damage;
    private final AuraSet auraSet;

    public BattlerModel(BaseBattlerCard baseCard, Ownership.Type owner) {
        this(new BattlerCardModel(baseCard, owner));
    }

    public BattlerModel(BattlerCardModel cardModel) {
        this.cardModel = cardModel;
        this.stats = new StatsModel(cardModel.getBaseBattlerCard().getBaseStats());
        this.auraSet = new AuraSet(AuraSupplier.empty);
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

    public Ownership getOwnership() {
        return cardModel.getOwnership();
    }

    @Override
    public int damage(int amount) {
        return damage += amount;
    }

    @Override
    public int heal(int amount) {
        return damage -= amount;
    }

    public void addAllAuras(Array<Aura> arr) {
        arr.addAll(auraSet.getAuras());
        cardModel.addAllAuras(arr);
    }
}
