package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;

public class SlotModel {
    private BattlerModel battler;
    private Array<Aura> auras;

    public BattlerModel getBattler() {
        return battler;
    }

    public void setBattler(BattlerModel battler) {
        this.battler = battler;
    }

    public boolean hasBattler() {
        return battler != null;
    }

    public void addAllAuras(Array<Aura> arr) {
        arr.addAll(auras);
    }
}
