package io.bloogames.deckbuilder.effect.source;

import io.bloogames.deckbuilder.model.ownership.Ownership;

public interface Source {
    Object model();
    Ownership.Type owner();
}
