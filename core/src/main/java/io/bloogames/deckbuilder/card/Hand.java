package io.bloogames.deckbuilder.card;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.scene2d.FannedGroup;
import io.bloogames.deckbuilder.scene2d.HoverListener;

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
        addHoverLogic(card);
        fan();
    }

    public void addHoverLogic(Card card) {
        card.addListener(new HoverListener(0.5f) {
            @Override
            public void onHoverStart(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                setSelectedActor(card);
            }

            @Override
            public void onHoverEnd(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                clearSelected();
            }
        });
    }
}
