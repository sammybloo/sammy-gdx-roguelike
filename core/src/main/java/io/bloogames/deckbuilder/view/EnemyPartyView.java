package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.handler.HandHoverHandler;
import io.bloogames.deckbuilder.model.BattlePartyModel;
import io.bloogames.deckbuilder.controller.BattleController;
import io.bloogames.deckbuilder.scene2d.FannedGroup;

public class EnemyPartyView extends PartyView {

    public EnemyPartyView(BattleController battleController, BattlePartyModel model) {
        super(model);
        setLeader(new LeaderView(model.getLeader()));
        getLeader().setPosition(15, 15);

        setHand(new HandView(model.getHand(),
            new FannedGroup.FanSettings(0.3f, 0.3f, 10f, 1.5f,
                1.05f, 180f, 0.2f),
            new HandHoverHandler(battleController.getBattleState(), 0.175f, 0.15f)));

        setTableau(new TableauView(model.getTableau()));

        register(getLeader(), new ResizeableSettings(200f, 200f, Align.topLeft)
            .padding(10, 10).keepAspect());
        register(getTableau(), new ResizeableSettings(WIDTH * 0.6f, HEIGHT * 0.4f, Align.bottom)
            .yOffset(10f));
        register(getHand(), new ResizeableSettings(WIDTH * 0.66f, HEIGHT * 0.5f, Align.top)
            .yOffset(HEIGHT * -0.4f));
        getHand().setRotation(180f);
    }
}
