package io.bloogames.deckbuilder.effect.trigger;

import io.bloogames.deckbuilder.view.ViewEvent;

public interface ViewEventListener<E extends ViewEvent> {
    void onEvent(E event);
}
