package io.bloogames.deckbuilder.event;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.effect.trigger.GameEventListener;
import io.bloogames.deckbuilder.model.GameModel;

public class GameEventDispatcher {
    private final Array<GameEventListener<? super GameEvent>> globalListeners = new Array<>();
    private final ObjectMap<Class<? extends GameEvent>, Array<GameEventListener<?>>> triggers = new ObjectMap<>();

    public <E extends GameEvent> void register(Class<E> eventType, GameEventListener<? super E> trigger) {
        Array<GameEventListener<?>> listeners = triggers.get(eventType);
        if (listeners == null) {
            listeners = new Array<>();
            triggers.put(eventType, listeners);
        }

        listeners.add(trigger);
    }

    public void registerAll(GameEventListener<? super GameEvent> listener) {
        globalListeners.add(listener);
    }

    public void dispatch(GameModel game, GameEvent event) {
        for (GameEventListener<? super GameEvent> listener : globalListeners) {
            listener.onEvent(game, event);
        }

        Array<GameEventListener<?>> listeners = triggers.get(event.getClass());
        if (listeners == null) return;

        for (GameEventListener<?> listener : listeners) {
            invoke(listener, game, event);
        }
    }

    @SuppressWarnings("unchecked")
    private <E extends GameEvent> void invoke(GameEventListener<?> trigger, GameModel game, GameEvent event) {
        ((GameEventListener<E>) trigger).onEvent(game, (E) event);
    }
}
