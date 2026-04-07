package io.bloogames.deckbuilder.ui;

public interface Highlightable {
    void setHighlightState(HighlightState state);

    default void clearHighlight() {
        setHighlightState(HighlightState.NONE);
    }
}
