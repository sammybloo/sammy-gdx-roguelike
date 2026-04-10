package io.bloogames.deckbuilder.effect.source;

import io.bloogames.deckbuilder.model.PartyModel;

public interface Source {
    String sourceId();

    PartyModel owner();
}
