package io.bloogames.deckbuilder.effect.source;

import io.bloogames.deckbuilder.model.ownership.Ownership;

public interface Source {
    String sourceId();

    Ownership.Type owner();
}
