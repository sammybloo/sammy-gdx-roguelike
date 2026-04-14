package io.bloogames.deckbuilder.effect.trigger;

import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.model.GameModel;

public interface GameEventListener<E extends GameEvent> {
    void onEvent(GameModel game, E event);
}
