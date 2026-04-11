package io.bloogames.deckbuilder.model.coordinator;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.event.GameEvent;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.effect.trigger.GameEventListener;
import io.bloogames.deckbuilder.effect.trigger.GameEventTrigger;

public class EventAdapter implements GameEventTrigger<GameEvent> {
    Array<GameEventListener<?>> eventListeners = new Array<>();

    @Override
    public Class<GameEvent> eventType() {
        return GameEvent.class;
    }

    @Override
    public void onEvent(GameEvent event, EffectExecutor executor) {
        for (GameEventListener<?> listener : eventListeners) {
            dispatch(listener, event);
        }
    }

    private <E extends GameEvent> void dispatch(GameEventListener<?> listener, GameEvent event) {
        @SuppressWarnings("unchecked")
        GameEventListener<E> typed = (GameEventListener<E>) listener;

        if (typed.eventType().isInstance(event)) {
            typed.onEvent(typed.eventType().cast(event));
        }
    }

    public void addSubscriber(GameEventListener<?> eventListener) {
        eventListeners.add(eventListener);
    }

    public void clear() {
        eventListeners.clear();
    }
}
