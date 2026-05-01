package io.bloogames.deckbuilder.vfx.concrete;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import io.bloogames.deckbuilder.vfx.VFXUtils;
import io.bloogames.deckbuilder.vfx.VisualEffect;
import io.bloogames.deckbuilder.view.CardView;
import io.bloogames.deckbuilder.view.DeckView;
import io.bloogames.deckbuilder.view.HandView;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;

public class DrawCardEffect implements VisualEffect {

    private final DeckView deck;
    private final CardView card;
    private final HandView hand;
    private final Action delay;

    public DrawCardEffect(DeckView deck, CardView card, HandView hand) {
        this.deck = deck;
        this.card = card;
        this.hand = hand;
        delay = delay(0.1f);
    }

    @Override
    public void play() {
        // TODO this is to flip cards from the enemy hand, and is obviously a hack. also, the enemy cards are ~20px off at the moment
        if (card.getRotation() > 175f) {
            card.moveBy(deck.getWidth() / 2 + card.getWidth() / 2 * card.getScaleX(),
                deck.getHeight() / 2 + card.getHeight() / 2 * card.getScaleY());
            card.rotateBy(-180f);
        }
        VFXUtils.unmoor(card);
        deck.removeCard(card);
        Vector2 handPosition = hand.stageToLocalCoordinates(new Vector2(card.getX(), card.getY()));
        hand.addCard(card);
        card.setPosition(handPosition.x, handPosition.y);
        hand.getStage().addAction(delay);
        deck.sync();
    }

    @Override
    public boolean isReady() {
        return delay.getActor() == null;
    }
}
