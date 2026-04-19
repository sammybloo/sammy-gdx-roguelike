package io.bloogames.deckbuilder.effect.target;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.model.PartyModel;

public interface Target {
    PartyModel owner();

    Array<TargetType> types();
}
