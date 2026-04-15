package io.bloogames.deckbuilder.model.coordinator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.effect.trigger.GameEventListener;
import io.bloogames.deckbuilder.effect.trigger.ViewEventListener;
import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.event.GameEventDispatcher;
import io.bloogames.deckbuilder.model.GameModel;
import io.bloogames.deckbuilder.view.event.ViewEvent;

public class ViewEventBus implements GameEventListener<GameEvent> {
    private final ObjectMap<Class<? extends ViewEvent>, Array<ViewEventListener<?>>> eventListeners = new ObjectMap<>();

    public ViewEventBus(GameEventDispatcher eventDispatcher) {
        eventDispatcher.registerAll(this);
    }

    @Override
    public void onEvent(GameModel game, GameEvent gameEvent) {
        ViewEvent viewEvent = mapEvent(gameEvent);
        dispatch(viewEvent);
    }

    public <E extends ViewEvent> void dispatch(E event) {
        Gdx.app.log(this.getClass().getName(), "View event: " + event);
        if (!eventListeners.containsKey(event.getClass())) {
            return;
        }
        for (ViewEventListener<?> listener : eventListeners.get(event.getClass())) {
            dispatch(listener, event);
        }
    }

    @SuppressWarnings("unchecked")
    private <E extends ViewEvent> void dispatch(ViewEventListener<?> listener, E event) {
        ((ViewEventListener<E>) listener).onEvent(event);
    }

    public <E extends ViewEvent> void register(Class<E> eventType, ViewEventListener<? super E> trigger) {
        Array<ViewEventListener<?>> subscribers = eventListeners.get(eventType);
        if (subscribers == null) {
            subscribers = new Array<>();
            eventListeners.put(eventType, subscribers);
        }

        subscribers.add(trigger);
    }

    private ViewEvent mapEvent(GameEvent gameEvent) {
        return switch (gameEvent) {
            case GameEvent.CardPlayedEvent e -> new ViewEvent.CardPlayedEvent(e.cardSource(), e.target());
            case GameEvent.BattleStateEvent e -> new ViewEvent.BattleStateEvent(e.oldState(), e.newState());
            case GameEvent.BattlerAddedEvent e -> new ViewEvent.BattlerAddedEvent(e.slot(), e.battler());
            case GameEvent.DamageDealtEvent e -> new ViewEvent.DamageDealtEvent(e.source(), e.target(), e.amount());
            case GameEvent.BattlersSwappedEvent e -> new ViewEvent.BattlerSwappedEvent(e.tableau(), e.slot1(), e.slot2());
        };
    }

    public void clear() {
        eventListeners.clear();
    }
}
