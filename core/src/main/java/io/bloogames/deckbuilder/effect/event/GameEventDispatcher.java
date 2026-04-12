package io.bloogames.deckbuilder.effect.event;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.effect.trigger.GameEventTrigger;

public class GameEventDispatcher {
    private final Array<GameEventTrigger<?>> subscribers = new Array<>();

    public void addSubscriber(GameEventTrigger<?> subscriber) {
        subscribers.add(subscriber);
    }

    public void clear() {
        subscribers.clear();
    }

    public void emit(GameEvent event, EffectExecutor executor) {
        for (int i = 0; i < subscribers.size; i++) {
            dispatch(subscribers.get(i), event, executor);
        }
    }

    private <E extends GameEvent> void dispatch(GameEventTrigger<?> listener, GameEvent event, EffectExecutor executor) {
        @SuppressWarnings("unchecked")
        GameEventTrigger<E> typed = (GameEventTrigger<E>) listener;

        if (typed.eventType().isInstance(event)) {
            typed.onEvent(typed.eventType().cast(event), executor);
        }
    }
}
