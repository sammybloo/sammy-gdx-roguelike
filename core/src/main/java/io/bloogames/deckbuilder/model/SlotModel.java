package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.data.AuraSupplier;
import io.bloogames.deckbuilder.data.BaseStats;
import io.bloogames.deckbuilder.effect.source.concrete.SlotSource;
import io.bloogames.deckbuilder.model.aura.AuraModel;
import io.bloogames.deckbuilder.model.aura.AuraSet;
import io.bloogames.deckbuilder.model.ownership.Ownership;
import io.bloogames.deckbuilder.model.stats.StatsModel;

public class SlotModel {
    private static final BaseStats SLOT_BASE_STATS = new BaseStats(0, 0);
    private final AuraSet auraSet;
    private final StatsModel stats = new StatsModel(SLOT_BASE_STATS);
    private final Ownership ownership;
    private BattlerModel battler;

    public SlotModel(Ownership.Type owner) {
        auraSet = new AuraSet(new SlotSource(this), AuraSupplier.empty);
        ownership = new Ownership(owner);
    }

    public BattlerModel getBattler() {
        return battler;
    }

    public void setBattler(BattlerModel battler) {
        this.battler = battler;
    }

    public Ownership getOwnership() {
        return ownership;
    }

    public boolean hasBattler() {
        return battler != null;
    }

    public void addAllAuras(Array<AuraModel> arr) {
        arr.addAll(auraSet.getAuras());
        if (hasBattler()) {
            battler.addAllAuras(arr);
        }
    }
}
