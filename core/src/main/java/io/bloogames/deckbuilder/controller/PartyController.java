package io.bloogames.deckbuilder.controller;

import io.bloogames.deckbuilder.vfx.VFXManager;
import io.bloogames.deckbuilder.vfx.concrete.DrawCardEffect;
import io.bloogames.deckbuilder.vfx.concrete.FlipCardFaceupVisualEffect;
import io.bloogames.deckbuilder.view.CardView;
import io.bloogames.deckbuilder.view.HandView;
import io.bloogames.deckbuilder.view.PartyView;
import io.bloogames.deckbuilder.view.event.ViewEvent;

public class PartyController {

    protected final HandController handController;
    protected final TableauController tableauController;
    protected final LeaderController leaderController;

    public PartyController(PartyView party, BattleController battleController) {
        handController = new HandController(party.getHand(), battleController);
        tableauController = new TableauController(party.getTableau(), battleController, party.getModel().getParty());
        leaderController = new LeaderController(party.getLeader(), party.getManaView(), battleController);

        battleController.getEventBus().register(ViewEvent.CardDrawnEvent.class, e -> {
            CardView cardView = party.getDeck().getCard(e.card());
            if (cardView != null) {
                VFXManager.INSTANCE.addEffect(new DrawCardEffect(party.getDeck(), cardView, party.getHand()));
                if (e.card().isFaceup()) {
                    VFXManager.INSTANCE.addEffect(new FlipCardFaceupVisualEffect(cardView));
                }
            }
        });
    }
}
