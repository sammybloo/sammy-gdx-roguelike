package io.bloogames.deckbuilder.effect.controller;

import io.bloogames.deckbuilder.view.CardView;
import io.bloogames.deckbuilder.view.HandView;

public interface HandController {

    void attach(HandView hand, CardView card);
}
