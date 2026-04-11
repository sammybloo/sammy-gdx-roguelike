package io.bloogames.deckbuilder.effect.trigger;

import io.bloogames.deckbuilder.effect.event.GameEvent;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;

public interface GameEventListener<E extends GameEvent> {
    Class<E> eventType();

    void onEvent(E event);
}
