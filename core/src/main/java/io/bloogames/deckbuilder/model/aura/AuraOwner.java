package io.bloogames.deckbuilder.model.aura;

import io.bloogames.deckbuilder.effect.source.Source;

public interface AuraOwner {
    Source getOwner();
    void addAura(Aura aura);
    void removeAura(Aura aura);
}
