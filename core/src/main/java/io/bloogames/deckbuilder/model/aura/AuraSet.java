package io.bloogames.deckbuilder.model.aura;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.source.Source;

public class AuraSet implements AuraOwner {
    private final Array<AuraModel> auras = new Array<>();
    private final Source source;

    public AuraSet(Source source, Array<AuraModel> auras) {
        this.source = source;
        addAllAuras(auras);
    }

    @Override
    public Source source() {
        return source;
    }

    public Array<AuraModel> getAuras() {
        return auras;
    }

    public void addAllAuras(Array<AuraModel> auras) {
        for (AuraModel aura : auras) {
            addAura(aura);
        }
    }

    @Override
    public void addAura(AuraModel newAura) {
        for (AuraModel existingAura : auras) {
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
    public void removeAura(AuraModel removedAura) {
        auras.removeValue(removedAura, true);
    }
}
