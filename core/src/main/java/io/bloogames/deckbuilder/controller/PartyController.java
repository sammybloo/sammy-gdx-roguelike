package io.bloogames.deckbuilder.controller;

import io.bloogames.deckbuilder.view.PartyView;

public class PartyController {

    protected final HandController handController;
    protected final TableauController tableauController;
    protected final LeaderController leaderController;

    public PartyController(PartyView party, BattleController battleController) {
        handController = new HandController(party.getHand(), battleController, party.getModel().getParty());
        tableauController = new TableauController(party.getTableau(), battleController, party.getModel().getParty());
        leaderController = new LeaderController(party.getLeader(), party.getManaView(), battleController);
    }
}
