package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.data.BaseTrinket;

public class TrinketModel {
    private BaseTrinket base;
    private Array<Aura> auras;

    public TrinketModel(BaseTrinket base) {
        this.base = base;
        this.auras = base.getAuras();
    }

    public void addAllAuras(Array<Aura> arr) {
        arr.addAll(auras);
    }
}
