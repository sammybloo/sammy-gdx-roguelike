package io.bloogames.deckbuilder.ui;


import com.badlogic.gdx.graphics.Color;

public enum HighlightState {
    NONE(Color.WHITE),
    VALID(new Color(0.8f, 1f, 0.8f, 1f)),
    INVALID(new Color(1f, 0.8f, 0.8f, 1f)),
    SELECTED(new Color(0.1f, 1f, 0.8f, 1f));

    private Color colour;

    HighlightState(Color colour) {
        this.colour = colour;
    }

    public Color getColour() {
        return colour;
    }
}
