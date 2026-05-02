package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.data.BaseTrinket;
import io.bloogames.deckbuilder.effect.source.concrete.TrinketSource;
import io.bloogames.deckbuilder.model.aura.AuraModel;
import io.bloogames.deckbuilder.model.aura.AuraSet;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public class TrinketModel {
    private final BaseTrinket base;
    private final AuraSet auraSet;
    private final Ownership ownership;

    public TrinketModel(BaseTrinket base, Ownership.Type owner) {
        this.base = base;
        this.auraSet = new AuraSet(new TrinketSource(this), base.getAuras());
        this.ownership = new Ownership(owner);
    }

    public void addAllAuras(Array<AuraModel> arr) {
        arr.addAll(auraSet.getAuras());
    }

    public Ownership getOwnership() {
        return ownership;
    }
}
