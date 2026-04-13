package io.bloogames.deckbuilder.effect.trigger;

import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.model.BattleModel;

public interface GameEventListener<E extends GameEvent> {
    void onEvent(BattleModel battle, E event);
}
