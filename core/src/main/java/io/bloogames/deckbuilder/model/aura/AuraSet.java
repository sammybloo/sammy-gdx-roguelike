package io.bloogames.deckbuilder.model.aura;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.source.Source;

public class AuraSet implements AuraOwner {
    private final Array<Aura> auras;

    public AuraSet(Array<Aura> auras) {
        this.auras = auras;
    }

    @Override
    public Source getOwner() {
        return null;
    }

    public Array<Aura> getAuras() {
        return auras;
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
