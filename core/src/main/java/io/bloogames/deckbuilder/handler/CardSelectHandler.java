package io.bloogames.deckbuilder.handler;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import io.bloogames.deckbuilder.controller.BattleController;
import io.bloogames.deckbuilder.model.BattlePartyModel;
import io.bloogames.deckbuilder.scene2d.HoverListener;
import io.bloogames.deckbuilder.view.CardView;
import io.bloogames.deckbuilder.view.HandView;

public class CardSelectHandler implements HandHandler {

    private final BattleController battleController;
    private final BattlePartyModel owner;

    public CardSelectHandler(BattleController battleController, BattlePartyModel owner) {
        this.battleController = battleController;
        this.owner = owner;
    }

    public void attach(HandView hand, CardView card) {
        card.addListener(new HoverListener(0f, 0f) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (battleController.getBattleState().canSelectCards()) {
                    battleController.startPlayCard(card.getModel(), owner);
                }
            }
        });
    }
}
