package io.bloogames.deckbuilder.model.aura;

import io.bloogames.deckbuilder.effect.source.Source;

public interface AuraOwner {
    Source source();

    void addAura(AuraModel aura);

    void removeAura(AuraModel aura);
}
