package io.bloogames.deckbuilder.data;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.model.aura.Aura;

public class BaseTrinket {
    AuraSupplier auraSupplier;

    public BaseTrinket(Array<Aura> auras) {
        auraSupplier = new AuraSupplier(auras);
    }

    public Array<Aura> getAuras() {
        return auraSupplier.get();
    }
}
