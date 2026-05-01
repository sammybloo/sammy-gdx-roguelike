package io.bloogames.deckbuilder.model.aura;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.source.Source;

public class AuraSet implements AuraOwner {
    private final Array<Aura> auras = new Array<>();
    private final Source source;

    public AuraSet(Source source, Array<Aura> auras) {
        this.source = source;
        addAllAuras(auras);
    }

    @Override
    public Source source() {
        return source;
    }

    public Array<Aura> getAuras() {
        return auras;
    }

    public void addAllAuras(Array<Aura> auras) {
        for (Aura aura : auras) {
            addAura(aura);
        }
    }

    @Override
    public void addAura(Aura newAura) {
        for (Aura existingAura : auras) {
            if (existingAura.getId().equals(newAura.getId())) {
                if (newAura instanceof StackableAura newStackableAura
                    && existingAura instanceof StackableAura existingStackableAura) {
                    existingStackableAura.addStacks(newStackableAura.getStacks());
                    return;
                }
            }
        }
        auras.add(newAura);
        newAura.setOwner(this);
    }

    @Override
    public void removeAura(Aura removedAura) {
        auras.removeValue(removedAura, true);
    }
}
