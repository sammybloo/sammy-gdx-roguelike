package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.GameConstants;
import io.bloogames.deckbuilder.model.aura.Aura;
import io.bloogames.deckbuilder.model.ownership.Ownership;

import java.util.Optional;

public class BattlePartyModel {
    private final PartyModel party;
    private final TableauModel tableau;
    private final HandModel hand;
    private final DeckModel deck;
    private final DiscardPileModel discardPile;

    public BattlePartyModel(PartyModel party) {
        this.party = party;
        this.tableau = new TableauModel(GameConstants.NUM_SLOTS, getOwnership().getCurrentOwner());
        this.hand = new HandModel(GameConstants.MAX_HAND_SIZE, getOwnership().getCurrentOwner());
        this.deck = new DeckModel(party.getFullDeck().getCards(), getOwnership().getCurrentOwner());
        this.discardPile = new DiscardPileModel(getOwnership().getCurrentOwner());
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

    public DiscardPileModel getDiscardPile() {
        return discardPile;
    }

    public Ownership getOwnership() {
        return party.getOwnership();
    }

    public void addAllAuras(Array<Aura> arr) {
        party.addAllAuras(arr);
        tableau.addAllAuras(arr);
    }

    public boolean hasCard(CardModel card) {
        return hand.contains(card) || deck.contains(card) || discardPile.contains(card);
    }

    public Optional<CardZone> getCardZone(CardModel card) {
        if (deck.contains(card)) {
            return Optional.of(deck);
        }
        if (hand.contains(card)) {
            return Optional.of(hand);
        }
        if (discardPile.contains(card)) {
            return Optional.of(discardPile);
        }
        return Optional.empty();
    }

    public CardZone getCardZone(CardZone.Type type) {
        switch (type) {
            case HAND -> {
                return hand;
            }
            case DECK -> {
                return deck;
            }
            case DISCARD_PILE -> {
                return discardPile;
            }
        }
        return null;
    }
}
