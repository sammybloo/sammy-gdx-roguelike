package io.bloogames.deckbuilder.vfx.concrete;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.ui.ViewUtils;
import io.bloogames.deckbuilder.vfx.VisualEffect;
import io.bloogames.deckbuilder.view.CardView;
import io.bloogames.deckbuilder.view.DeckView;
import io.bloogames.deckbuilder.view.HandView;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class DrawCardEffect implements VisualEffect {

    private DeckView deck;
    private CardView card;
    private HandView hand;
    private Action delay;

    public DrawCardEffect(DeckView deck, CardView card, HandView hand) {
        this.deck = deck;
        this.card = card;
        this.hand = hand;
        delay = delay(0.1f);
    }

    @Override
    public void play() {
        if (card.getRotation() > 175f) {
            card.moveBy(deck.getWidth() / 2 + card.getWidth() / 2 * card.getScaleX(),
                deck.getHeight() / 2 + card.getHeight() / 2 * card.getScaleY());
            card.rotateBy(-180f);
        }
        ViewUtils.unmoor(card);
        deck.removeCard(card);
        Vector2 handPosition = hand.stageToLocalCoordinates(new Vector2(card.getX(), card.getY()));
        hand.addCard(card);
        card.setPosition(handPosition.x, handPosition.y);
        hand.getStage().addAction(delay);
    }

    @Override
    public boolean isReady() {
        return delay.getActor() == null;
    }
}
