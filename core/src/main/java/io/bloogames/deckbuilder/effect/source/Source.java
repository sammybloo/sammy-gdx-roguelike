package io.bloogames.deckbuilder.effect.source;

import io.bloogames.deckbuilder.model.BattlePartyModel;

public interface Source {
    String sourceId();

    BattlePartyModel owner();
}
