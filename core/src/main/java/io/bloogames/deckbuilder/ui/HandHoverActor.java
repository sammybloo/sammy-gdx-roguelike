package io.bloogames.deckbuilder.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import io.bloogames.deckbuilder.view.CardView;
import io.bloogames.deckbuilder.view.HandView;

public class HandHoverActor extends Actor {

    private final HandView hand;
    private final Vector2 mouse = new Vector2();
    private CardView hoveredCard;

    public HandHoverActor(HandView hand) {
        this.hand = hand;
        setTouchable(Touchable.disabled);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        Stage stage = getStage();
        if (stage == null) return;

        mouse.set(Gdx.input.getX(), Gdx.input.getY());
        stage.screenToStageCoordinates(mouse);

        Actor hit = stage.hit(mouse.x, mouse.y, true);
        CardView card = findCardInHand(hit);

        if (card == hoveredCard) {
            return;
        }

        if (hoveredCard != null) {
            hand.unselectActor(hoveredCard);
        }

        hoveredCard = card;

        if (hoveredCard != null && hoveredCard.isFaceup()) {
            hand.setSelectedActor(hoveredCard);
        }
    }

    private CardView findCardInHand(Actor actor) {
        while (actor != null) {
            if (actor instanceof CardView cardView && hand.containsCard(cardView.getModel())) {
                return cardView;
            }
            actor = actor.getParent();
        }
        return null;
    }

    public void clearHover() {
        if (hoveredCard != null) {
            hand.unselectActor(hoveredCard);
            hoveredCard = null;
        }
    }
}
