package io.bloogames.deckbuilder.handler;

import io.bloogames.deckbuilder.view.CardView;
import io.bloogames.deckbuilder.view.HandView;

public interface HandHandler {
    void attach(HandView hand, CardView card);
}
