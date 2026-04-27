package io.bloogames.deckbuilder.effect.target;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public interface Target {
    Ownership.Type owner();

    Array<TargetType> types();
}
