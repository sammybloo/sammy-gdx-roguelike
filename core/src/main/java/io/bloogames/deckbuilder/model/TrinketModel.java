package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.data.BaseTrinket;
import io.bloogames.deckbuilder.model.aura.Aura;
import io.bloogames.deckbuilder.model.aura.AuraSet;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public class TrinketModel {
    private BaseTrinket base;
    private AuraSet auraSet;
    private Ownership ownership;

    public TrinketModel(BaseTrinket base, Ownership.Type owner) {
        this.base = base;
        this.auraSet = new AuraSet(base.getAuras());
        this.ownership = new Ownership(owner);
    }

    public void addAllAuras(Array<Aura> arr) {
        arr.addAll(auraSet.getAuras());
    }
}
