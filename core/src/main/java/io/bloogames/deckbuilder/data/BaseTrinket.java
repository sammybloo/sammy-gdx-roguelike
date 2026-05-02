package io.bloogames.deckbuilder.data;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.model.aura.AuraModel;

public class BaseTrinket {
    AuraSupplier auraSupplier;

    public BaseTrinket(Array<AuraModel> auras) {
        auraSupplier = new AuraSupplier(auras);
    }

    public Array<AuraModel> getAuras() {
        return auraSupplier.get();
    }
}
