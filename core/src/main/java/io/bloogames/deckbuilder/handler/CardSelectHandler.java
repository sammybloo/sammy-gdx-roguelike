package io.bloogames.deckbuilder.handler;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.bloogames.deckbuilder.controller.BattleController;
import io.bloogames.deckbuilder.view.CardView;
import io.bloogames.deckbuilder.view.HandView;

public class CardSelectHandler implements HandHandler {

    private final BattleController battleController;

    public CardSelectHandler(BattleController battleController) {
        this.battleController = battleController;
    }

    public void attach(HandView hand, CardView card) {
        card.addListener(
            new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (battleController.getBattleState().canSelectCards()) {
                        battleController.startPlayCard(card.getModel());
                    }
                }
            }
        );
    }
}
