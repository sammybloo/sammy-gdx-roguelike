package io.bloogames.deckbuilder.card;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class Hand extends Group {

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
        fanHand(1);
    }

    public void fanHand() {
        fanHand(0f);
    }

    public void fanHand(float duration) {
        if (cards.size == 0) return;

        float overlap = 0.35f;   // 0 = no overlap, 1 = full overlap
        float lift = 100f;
        float maxRotation = 16f;

        float handWidth = getWidth();
        float cardWidth = cards.first().getWidth() * cards.first().getScaleX();

        float spacing = cardWidth * (1f - overlap);
        float totalWidth = spacing * (cards.size - 1) + cardWidth;
        float startX = (handWidth - totalWidth) / 2f;

        for (int i = 0; i < cards.size; i++) {
            Card card = cards.get(i);

            float t = cards.size == 1 ? 0.5f : i / (float) (cards.size - 1);
            float centered = t * 2f - 1f;

            float x = startX + i * spacing;
            float y = lift * (1f - centered * centered);
            float rotation = -maxRotation * centered;

            card.setOrigin(Align.center);

            var moveToAction = new MoveToAction();
            moveToAction.setPosition(x, y);
            moveToAction.setDuration(duration);
            card.addAction(moveToAction);

            var rotateAction = new RotateToAction();
            rotateAction.setRotation(rotation);
            rotateAction.setDuration(duration);
            card.addAction(rotateAction);
        }
    }
}

