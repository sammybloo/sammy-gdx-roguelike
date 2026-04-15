package io.bloogames.deckbuilder.controller;

import io.bloogames.deckbuilder.effect.target.concrete.CardTarget;
import io.bloogames.deckbuilder.model.PartyModel;
import io.bloogames.deckbuilder.ui.HighlightState;
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
        CardView cardView = hand.getCardView(event.cardSource().card());
        if (cardView != null) {
            hand.sync();
            cardView.disappear();
            hand.removeCard(cardView.getModel());
        }

        for (CardView card : hand.getCardViews()) {
            if (battleController.isValidTarget(event.cardSource(), new CardTarget(card.getModel(), owner))) {
                card.setHighlightState(HighlightState.VALID);
            }
            else {
                card.setHighlightState(HighlightState.INVALID);
            }
        }
    }
}
