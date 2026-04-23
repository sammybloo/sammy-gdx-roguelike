package io.bloogames.deckbuilder.controller;

import io.bloogames.deckbuilder.view.PlayerPartyView;

public class PlayerPartyController extends PartyController {
    public PlayerPartyController(PlayerPartyView partyView, BattleController battleController) {
        super(partyView, battleController);
        tableauController.enableSwapping();
        new LeaderMessageController(partyView.getLeaderMessageView(), battleController);
    }
}
