package io.bloogames.deckbuilder.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import io.bloogames.deckbuilder.model.coordinator.BattleCoordinator;
import io.bloogames.deckbuilder.scene2d.HoverListener;
import io.bloogames.deckbuilder.view.CardView;
import io.bloogames.deckbuilder.view.HandView;

public class HandHoverController implements HandController {

    private final BattleCoordinator coordinator;
    private final float hoverDelay;
    private final float unhoverDelay;

    public HandHoverController(BattleCoordinator coordinator, float hoverDelay, float unhoverDelay) {
        this.coordinator = coordinator;
        this.hoverDelay = hoverDelay;
        this.unhoverDelay = unhoverDelay;
    }

    public void attach(HandView hand, CardView card) {
        card.addListener(new HoverListener(hoverDelay, unhoverDelay) {
            @Override
            public void onHoverStart(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (coordinator.getInteractionManager().canHoverCards()) {
                    if (card.isFaceup()) {
                        hand.setSelectedActor(card);
                    }
                }
            }

            @Override
            public void onHoverEnd(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (coordinator.getInteractionManager().canHoverCards()) {
                    hand.unselectActor(card);
                }
            }
        });
    }
}
