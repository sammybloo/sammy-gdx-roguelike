package io.bloogames.deckbuilder.model;

public class PartyModel {
    private final LeaderModel leader;
    private final DeckModel fullDeck;

    public PartyModel(LeaderModel leader) {
        this.leader = leader;
        this.fullDeck = new DeckModel();
    }

    public LeaderModel getLeader() {
        return leader;
    }

    public DeckModel getFullDeck() {
        return fullDeck;
    }
}
