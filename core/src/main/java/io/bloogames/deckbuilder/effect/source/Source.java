package io.bloogames.deckbuilder.effect.source;

import io.bloogames.deckbuilder.model.ModelProperties;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public interface Source {
    Object model();

    Ownership.Type owner();

    default ModelProperties modelProperties() {
        return ModelProperties.EMPTY;
    }
}
