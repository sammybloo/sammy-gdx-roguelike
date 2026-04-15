package io.bloogames.deckbuilder.effect.target;

import io.bloogames.deckbuilder.model.PartyModel;

public interface Target {
    PartyModel owner();

    TargetType type();
}
