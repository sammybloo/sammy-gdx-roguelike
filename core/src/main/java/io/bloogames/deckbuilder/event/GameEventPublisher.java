package io.bloogames.deckbuilder.event;

public interface GameEventPublisher {
    void dispatch(GameEvent event);
}
