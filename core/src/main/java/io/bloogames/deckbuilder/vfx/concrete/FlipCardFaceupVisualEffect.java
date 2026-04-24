package io.bloogames.deckbuilder.vfx.concrete;

import io.bloogames.deckbuilder.vfx.VisualEffect;
import io.bloogames.deckbuilder.view.CardView;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class FlipCardFaceupVisualEffect implements VisualEffect {
    private CardView cardView;

    public FlipCardFaceupVisualEffect(CardView cardView) {
        this.cardView = cardView;
    }

    @Override
    public void play() {
        cardView.getCardBack().addAction(sequence(scaleTo(0f, 1f, 0.05f), visible(false)));
        cardView.getFrontFace().addAction(delay(0.05f, sequence(scaleTo(0f, 1f), visible(true), scaleTo(1f, 1f, 0.05f))));
        cardView.addAction(delay(0.1f, run(cardView::sync)));
    }

    @Override
    public boolean isReady() {
        return true;
    }
}
