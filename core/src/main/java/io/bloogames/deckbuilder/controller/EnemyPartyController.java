package io.bloogames.deckbuilder.controller;

import io.bloogames.deckbuilder.handler.HandHoverHandler;
import io.bloogames.deckbuilder.view.PartyView;

public class EnemyPartyController extends PartyController {
    public EnemyPartyController(PartyView party, BattleController battleController) {
        super(party, battleController);
        party.getHand().addHandler(new HandHoverHandler(battleController.getBattleState(), 0.3f, 0.1f));
    }
}
