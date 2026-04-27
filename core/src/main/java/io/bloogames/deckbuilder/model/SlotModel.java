package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.data.AuraSupplier;
import io.bloogames.deckbuilder.model.aura.Aura;
import io.bloogames.deckbuilder.model.aura.AuraSet;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public class SlotModel {
    private BattlerModel battler;
    private AuraSet auraSet;
    private Ownership ownership;

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
