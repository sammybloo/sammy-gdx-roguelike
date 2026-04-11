package io.bloogames.deckbuilder.controller;

import io.bloogames.deckbuilder.view.CardView;
import io.bloogames.deckbuilder.view.HandView;

public interface HandController {

    void attach(HandView hand, CardView card);
}
