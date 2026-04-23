package io.bloogames.deckbuilder.controller;

import io.bloogames.deckbuilder.model.PartyModel;
import io.bloogames.deckbuilder.vfx.DisappearEffect;
import io.bloogames.deckbuilder.vfx.VFXManager;
import io.bloogames.deckbuilder.view.CardView;
import io.bloogames.deckbuilder.view.HandView;
import io.bloogames.deckbuilder.view.event.ViewEvent;

public class HandController {
    private final HandView hand;

    public HandController(HandView hand, BattleController battleController) {
        this.hand = hand;
        battleController.getEventBus().register(ViewEvent.CardStartEvent.class, this::handleCardPlayed);
    }

    private void handleCardPlayed(ViewEvent.CardStartEvent event) {
        CardView card = hand.getCardView(event.cardSource().card());
        if (card != null) {
            hand.sync();
            VFXManager.INSTANCE.addEffect(new DisappearEffect(card));
            hand.removeCard(card.getModel());
        }
    }
}
