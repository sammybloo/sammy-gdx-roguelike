package io.bloogames.deckbuilder.controller;

import io.bloogames.deckbuilder.handler.CardSelectHandler;
import io.bloogames.deckbuilder.handler.HandHoverHandler;
import io.bloogames.deckbuilder.view.PlayerPartyView;

public class PlayerPartyController extends PartyController {
    public PlayerPartyController(PlayerPartyView partyView, BattleController battleController) {
        super(partyView, battleController);
        tableauController.enableSwapping();
        partyView.getHand().addHandler(new HandHoverHandler(battleController.getBattleState(), 0, 0.1f));
        partyView.getHand().addHandler(new CardSelectHandler(battleController));
        new LeaderMessageController(partyView.getLeaderMessageView(), battleController);
    }
}
