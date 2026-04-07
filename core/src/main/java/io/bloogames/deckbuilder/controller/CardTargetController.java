package io.bloogames.deckbuilder.controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Timer;
import io.bloogames.deckbuilder.scene2d.HoverListener;
import io.bloogames.deckbuilder.view.CardView;
import io.bloogames.deckbuilder.view.HandView;

public class CardTargetController implements HandController {

    public void attach(HandView hand, CardView card) {
        card.addListener(new HoverListener(0f, 0f) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hand.removeCard(card.getModel());
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        hand.update();
                    }
                }, 3f);
            }
        });
    }
}
