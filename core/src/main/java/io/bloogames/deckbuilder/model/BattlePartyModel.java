package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.GameConstants;

public class BattlePartyModel {
    private final PartyModel party;
    private final TableauModel tableau;
    private final HandModel hand;
    private final DeckModel deck;

    public BattlePartyModel(PartyModel party) {
        this.party = party;
        this.tableau = new TableauModel(GameConstants.NUM_SLOTS);
        this.hand = new HandModel(GameConstants.MAX_HAND_SIZE);
        this.deck = new DeckModel(party.getFullDeck().getCards());
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

    public DeckModel getDeck() {
        return deck;
    }

    public void addAllAuras(Array<Aura> arr) {
        party.addAllAuras(arr);
        tableau.addAllAuras(arr);
    }

    public boolean hasCard(CardModel card) {
        return hand.contains(card);
    }
}
