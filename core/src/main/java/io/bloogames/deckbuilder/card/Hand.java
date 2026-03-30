package io.bloogames.deckbuilder.card;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.utils.Array;

public class Hand extends Group {

    public static class CardLayout {
        public float x;
        public float y;
        public float rotation;
        public float scale;

        public CardLayout(float x, float y, float rotation, float scale) {
            this.x = x;
            this.y = y;
            this.rotation = rotation;
            this.scale = scale;
        }
    }

    private final Array<Card> cards;
    private final int maxSize;

    private float overlap = 0.3f;
    private float lift = 80f;
    private float maxRotation = 16f;
    private float normalScale = 0.5f;
    private float selectedScale = 0.8f;
    private float selectedLift = 150f;
    private float duration = 0.5f;

    private int selectedIndex = -1;

    public Hand(int maxSize) {
        this.cards = new Array<>();
        this.maxSize = maxSize;
    }

    public void addCard(Card card) {
        if (cards.size >= maxSize) return;

        cards.add(card);
        addActor(card);
        fanHand();
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        fanHand();
    }

    public void clearSelected() {
        this.selectedIndex = -1;
        fanHand();
    }

    public CardLayout getCardLayout(int index) {
        if (cards.size == 0) return new CardLayout(0f, 0f, 0f, normalScale);

        float handWidth = getWidth();
        float cardWidth = cards.first().getWidth() * normalScale;
        float selectedCardAdjustment = (cards.first().getWidth() * selectedScale * (1 - overlap)) / 2;

        float spacing = cardWidth * (1f - overlap);
        float totalWidth = spacing * (cards.size) + cardWidth;
        float startX = (handWidth - totalWidth) / 2f;

        float t = cards.size == 1 ? 0.5f : index / (float) (cards.size - 1);
        float centered = t * 2f - 1f;

        float x = startX + index * spacing;
        float y = lift * (1f - centered * centered);
        float rotation = -maxRotation * centered;
        float scale = normalScale;

        if (index == selectedIndex) {
            rotation = 0f;
            scale = selectedScale;
            y = selectedLift;
        } else if (selectedIndex != -1) {
            if (index < selectedIndex) {
                x -= selectedCardAdjustment;
            } else {
                x += selectedCardAdjustment;
            }
        }

        return new CardLayout(x, y, rotation, scale);
    }

    public void fanHand() {
        for (int i = 0; i < cards.size; i++) {
            Card card = cards.get(i);
            CardLayout layout = getCardLayout(i);

            card.clearActions();
            card.setZIndex(i);
            card.setOrigin(card.getWidth() / 2f, card.getHeight() / 2f);
            card.addAction(Actions.scaleTo(layout.scale, layout.scale, duration));
            card.addAction(Actions.moveTo(layout.x, layout.y, duration));
            card.addAction(Actions.rotateTo(layout.rotation, duration));

//            card.setScale(layout.scale);
//            card.setPosition(layout.x, layout.y);
//            card.setRotation(layout.rotation);
        }

        if (selectedIndex >= 0 && selectedIndex < cards.size) {
            cards.get(selectedIndex).toFront();
        }
    }
}
