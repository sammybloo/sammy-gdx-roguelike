package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.data.AuraSupplier;
import io.bloogames.deckbuilder.data.BaseBattlerCard;
import io.bloogames.deckbuilder.effect.source.concrete.BattlerSource;
import io.bloogames.deckbuilder.model.aura.Aura;
import io.bloogames.deckbuilder.model.aura.AuraSet;
import io.bloogames.deckbuilder.model.death.Death;
import io.bloogames.deckbuilder.model.ownership.Ownership;
import io.bloogames.deckbuilder.model.stats.Stats;

public class BattlerModel implements Damageable {
    private final BattlerCardModel cardModel;
    private final Stats stats;
    private int damage;
    private final AuraSet auraSet;
    private Array<Death> deaths = new Array<>();

    public BattlerModel(BaseBattlerCard baseCard, Ownership.Type owner) {
        this(new BattlerCardModel(baseCard, owner));
    }

    public BattlerModel(BattlerCardModel cardModel) {
        this.cardModel = cardModel;
        this.stats = new Stats(cardModel.getBaseBattlerCard().getBaseStats());
        this.auraSet = new AuraSet(new BattlerSource(this), new AuraSupplier(cardModel.getAuras()).get());
    }

    public String getBattlerId() {
        return cardModel.getCardId();
    }

    public int getPower() {
        return stats.getCurrentStats().power();
    }

    public int getMaxHealth() {
        return stats.getCurrentStats().health();
    }

    public Stats getStats() {
        return stats;
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

    public boolean isMarkedForDeath() {
        return deaths.size > 0;
    }

    public void markForDeath(Death death) {
        this.deaths.add(death);
    }

    public Death popDeath() {
        return deaths.removeIndex(0);
    }

    public int getDamage() {
        return damage;
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
        // cardModel.addAllAuras(arr);
    }
}
