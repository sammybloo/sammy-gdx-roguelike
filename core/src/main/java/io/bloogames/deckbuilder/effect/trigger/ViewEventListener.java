package io.bloogames.deckbuilder.effect.trigger;

import io.bloogames.deckbuilder.view.event.ViewEvent;

public interface ViewEventListener<E extends ViewEvent> {
    void onEvent(E event);
}
