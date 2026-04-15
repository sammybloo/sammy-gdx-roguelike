package io.bloogames.deckbuilder.handler;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import io.bloogames.deckbuilder.controller.BattleStateController;
import io.bloogames.deckbuilder.scene2d.HoverListener;
import io.bloogames.deckbuilder.view.CardView;
import io.bloogames.deckbuilder.view.HandView;

public class HandHoverHandler implements HandHandler {

    private final BattleStateController stateController;
    private final float hoverDelay;
    private final float unhoverDelay;

    public HandHoverHandler(BattleStateController stateController, float hoverDelay, float unhoverDelay) {
        this.stateController = stateController;
        this.hoverDelay = hoverDelay;
        this.unhoverDelay = unhoverDelay;
    }

    public void attach(HandView hand, CardView card) {
        card.addListener(new HoverListener(hoverDelay, unhoverDelay) {
            @Override
            public void onHoverStart(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (stateController.canHoverCards()) {
                    if (card.isFaceup()) {
                        hand.setSelectedActor(card);
                    }
                }
            }

            @Override
            public void onHoverEnd(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (stateController.canHoverCards()) {
                    hand.unselectActor(card);
                }
            }
        });
    }
}
