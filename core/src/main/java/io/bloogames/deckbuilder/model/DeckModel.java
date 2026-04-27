package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.manager.SeedManager;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public class DeckModel {
    private final Array<CardModel> cards = new Array<>();
    private final Ownership ownership;

    public DeckModel(Ownership.Type owner) {
        ownership = new Ownership(owner);
    }

    public DeckModel(Array<CardModel> cards, Ownership.Type owner) {
        this(owner);
        this.cards.addAll(cards);
    }

    public void addCard(CardModel card) {
        cards.add(card);
    }

    public boolean removeCard(CardModel card) {
        return cards.removeValue(card, true);
    }

    public Array<CardModel> getCards() {
        return cards;
    }

    public Ownership getOwnership() {
        return ownership;
    }

    public CardModel removeTopCard() {
        return cards.pop();
    }

    public void shuffle() {
        SeedManager.INSTANCE.shuffle(cards);
        for (CardModel card : cards) {
            card.setFaceup(false);
        }
    }
}
