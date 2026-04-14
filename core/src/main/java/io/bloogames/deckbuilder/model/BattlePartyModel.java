package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.GameConstants;

public class BattlePartyModel {
    private PartyModel party;
    private TableauModel tableau;
    private HandModel hand;

    public BattlePartyModel(PartyModel party) {
        this.party = party;
        this.tableau = new TableauModel(GameConstants.NUM_SLOTS);
        this.hand = new HandModel(GameConstants.MAX_HAND_SIZE);
    }

    public PartyModel getParty() {
        return party;
    }

    public LeaderModel getLeader() {
        return party.getLeader();
    }

    public TableauModel getTableau() {
        return tableau;
    }

    public HandModel getHand() {
        return hand;
    }

    public boolean hasCard(CardModel card) {
        return hand.contains(card);
    }
}
