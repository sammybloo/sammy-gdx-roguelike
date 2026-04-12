package io.bloogames.deckbuilder.controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Timer;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.model.PartyModel;
import io.bloogames.deckbuilder.model.coordinator.BattleCoordinator;
import io.bloogames.deckbuilder.scene2d.HoverListener;
import io.bloogames.deckbuilder.view.CardView;
import io.bloogames.deckbuilder.view.HandView;

public class CardSelectController implements HandController {

    private final BattleCoordinator coordinator;
    private final PartyModel owner;

    public CardSelectController(BattleCoordinator coordinator, PartyModel owner) {
        this.coordinator = coordinator;
        this.owner = owner;
    }

    public void attach(HandView hand, CardView card) {
        card.addListener(new HoverListener(0f, 0f) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (coordinator.getInteractionManager().canSelectCards()) {
                    if (coordinator.getCardCoordinator().canPlayCard(new CardSource(card.getModel(), owner))) {
                        hand.removeCard(card.getModel());
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                hand.sync();
                            }
                        }, 3f);
                    }
                }
            }
        });
    }
}
