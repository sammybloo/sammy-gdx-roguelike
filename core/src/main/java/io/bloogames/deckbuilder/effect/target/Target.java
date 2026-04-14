package io.bloogames.deckbuilder.effect.target;

import io.bloogames.deckbuilder.model.BattlePartyModel;

public interface Target {
    BattlePartyModel owner();

    TargetType type();
}
