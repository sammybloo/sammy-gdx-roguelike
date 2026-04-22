package io.bloogames.deckbuilder.ui.color;

public interface Tintable {
    void addTint(Tint tint);

    void removeTint(Tint tint);

    void refreshColour();
}
