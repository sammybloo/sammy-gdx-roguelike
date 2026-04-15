package io.bloogames.deckbuilder.controller;

import io.bloogames.deckbuilder.model.PartyModel;
import io.bloogames.deckbuilder.view.CardView;
import io.bloogames.deckbuilder.view.HandView;
import io.bloogames.deckbuilder.view.event.ViewEvent;

public class HandController {
    private final HandView hand;
    private final BattleController battleController;
    private final PartyModel owner;

    public HandController(HandView hand, BattleController battleController, PartyModel owner) {
        this.hand = hand;
        this.battleController = battleController;
        this.owner = owner;
        battleController.getEventBus().register(ViewEvent.CardStartEvent.class, this::handleCardPlayed);
    }

    private void handleCardPlayed(ViewEvent.CardStartEvent event) {
        CardView card = hand.getCardView(event.cardSource().card());
        if (card != null) {
            hand.sync();
            card.disappear();
            hand.removeCard(card.getModel());
        }
    }
}
