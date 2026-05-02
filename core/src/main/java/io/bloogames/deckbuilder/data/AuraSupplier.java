package io.bloogames.deckbuilder.data;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.model.aura.AuraModel;

public class AuraSupplier {
    // This isn't totally safe but. Y'know.
    public static Array<AuraModel> empty = new Array<>();

    private Array<AuraModel> auras;

    public AuraSupplier(Array<AuraModel> auras) {
        this.auras = auras;
        this.auras = get();
    }

    public Array<AuraModel> get() {
        Array<AuraModel> copy = new Array<>();
        for (AuraModel aura : auras) {
            copy.add(aura.copy());
        }
        return copy;
    }
}
