package io.bloogames.deckbuilder.vfx;

public interface VisualEffect {
    void play();

    boolean isReady();

    boolean isFinished();
}
