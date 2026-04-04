package io.bloogames.deckbuilder.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import io.bloogames.deckbuilder.scene2d.HoverListener;
import io.bloogames.deckbuilder.view.Card;
import io.bloogames.deckbuilder.view.Hand;

public class EnemyHandController implements HandController {

    public void attach(Hand hand, Card card) {
        card.addListener(new HoverListener(0.175f, 0.15f) {
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
