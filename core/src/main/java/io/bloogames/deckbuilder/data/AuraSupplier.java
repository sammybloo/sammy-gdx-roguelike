package io.bloogames.deckbuilder.data;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.model.aura.Aura;

public class AuraSupplier {
    // This isn't totally safe but. Y'know.
    public static Array<Aura> empty = new Array<>();

    private Array<Aura> auras;

    public AuraSupplier(Array<Aura> auras) {
        this.auras = auras;
        this.auras = get();
    }

    public Array<Aura> get() {
        Array<Aura> copy = new Array<>();
        for (Aura aura : auras) {
            copy.add(aura.copy());
        }
        return copy;
    }
}
