package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.data.AuraSupplier;
import io.bloogames.deckbuilder.data.BaseStats;
import io.bloogames.deckbuilder.model.aura.Aura;
import io.bloogames.deckbuilder.model.aura.AuraSet;
import io.bloogames.deckbuilder.model.ownership.Ownership;
import io.bloogames.deckbuilder.model.stats.Stats;

public class SlotModel {
    private static final BaseStats SLOT_BASE_STATS = new BaseStats(0, 0);

    private BattlerModel battler;
    private final AuraSet auraSet;
    private final Stats stats = new Stats(SLOT_BASE_STATS);
    private final Ownership ownership;

    public SlotModel(Ownership.Type owner) {
        auraSet = new AuraSet(AuraSupplier.empty);
        ownership = new Ownership(owner);
    }

    public BattlerModel getBattler() {
        return battler;
    }

    public Ownership getOwnership() {
        return ownership;
    }

    public void setBattler(BattlerModel battler) {
        this.battler = battler;
    }

    public boolean hasBattler() {
        return battler != null;
    }

    public void addAllAuras(Array<Aura> arr) {
        arr.addAll(auraSet.getAuras());
    }
}
