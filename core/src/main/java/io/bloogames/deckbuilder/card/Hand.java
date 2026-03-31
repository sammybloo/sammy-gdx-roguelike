package io.bloogames.deckbuilder.card;

import com.badlogic.gdx.utils.Array;

public class Hand extends FannedGroup {

    private final Array<Card> cards;
    private final int maxSize;

    public Hand(int maxSize) {
        this.cards = new Array<>();
        this.maxSize = maxSize;
    }

    public void addCard(Card card) {
        if (cards.size >= maxSize) return;

        cards.add(card);
        addActor(card);
        fan();
    }
}
