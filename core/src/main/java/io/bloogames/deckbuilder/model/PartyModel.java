package io.bloogames.deckbuilder.model;

public class PartyModel {
    private LeaderModel leader;
    private TableauModel tableau;
    private HandModel hand;

    public PartyModel(LeaderModel leader, TableauModel tableau, HandModel hand) {
        this.leader = leader;
        this.tableau = tableau;
        this.hand = hand;
    }

    public LeaderModel getLeader() {
        return leader;
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
