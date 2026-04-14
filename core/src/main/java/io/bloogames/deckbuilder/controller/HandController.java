package io.bloogames.deckbuilder.controller;

import io.bloogames.deckbuilder.view.CardView;
import io.bloogames.deckbuilder.view.HandView;
import io.bloogames.deckbuilder.view.event.ViewEvent;

public class HandController {
    private final HandView hand;
    private final BattleController battleController;

    public HandController(HandView hand, BattleController battleController) {
        this.hand = hand;
        this.battleController = battleController;
        battleController.getEventBus().register(ViewEvent.CardStartEvent.class, this::handleCardPlayed);
    }

    private void handleCardPlayed(ViewEvent.CardStartEvent event) {
        CardView cardView = hand.getCardView(event.cardSource().card());
        if (cardView != null) {
            cardView.disappear();
            hand.removeCard(cardView.getModel());
        }
    }


}
