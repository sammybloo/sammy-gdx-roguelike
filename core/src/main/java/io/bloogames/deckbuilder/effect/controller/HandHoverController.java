package io.bloogames.deckbuilder.effect.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import io.bloogames.deckbuilder.scene2d.HoverListener;
import io.bloogames.deckbuilder.view.CardView;
import io.bloogames.deckbuilder.view.HandView;

public class HandHoverController implements HandController {

    private float hoverDelay;
    private float unhoverDelay;

    public HandHoverController(float hoverDelay, float unhoverDelay) {
        this.hoverDelay = hoverDelay;
        this.unhoverDelay = unhoverDelay;
    }

    public void attach(HandView hand, CardView card) {
        card.addListener(new HoverListener(hoverDelay, unhoverDelay) {
            @Override
            public void onHoverStart(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (card.isFaceup()) {
                    hand.setSelectedActor(card);
                }
            }

            @Override
            public void onHoverEnd(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hand.unselectActor(card);
            }
        });
    }
}
