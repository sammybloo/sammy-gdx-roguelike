package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.controller.BattleController;
import io.bloogames.deckbuilder.handler.HandHoverHandler;
import io.bloogames.deckbuilder.model.BattlePartyModel;
import io.bloogames.deckbuilder.scene2d.FannedGroup;
import io.bloogames.deckbuilder.scene2d.ResizableSettings;

public class EnemyPartyView extends PartyView {

    public EnemyPartyView(BattleController battleController, BattlePartyModel model) {
        super(model);
        setLeader(new LeaderView(model.getLeader()));

        setHand(new HandView(model.getHand(),
            new FannedGroup.FanSettings(0.3f, 0.3f, 10f, 1.5f,
                1.045f, 180f, 0.15f),
            new HandHoverHandler(battleController.getBattleState(), 0.3f, 0.1f)));

        setTableau(new TableauView(model.getTableau()));

        setManaView(new ManaView(model.getLeader()));

        register(getTableau(), new ResizableSettings(WIDTH * 0.6f, HEIGHT * 0.4f, Align.bottom)
            .yOffset(10f));
        register(getHand(), new ResizableSettings(WIDTH * 0.66f, HEIGHT * 0.5f, Align.top)
            .yOffset(HEIGHT * -0.4f));
        register(getLeader(), new ResizableSettings(200f, 200f, Align.topLeft)
            .padding(10, 10).keepAspect());
        register(getManaView(), new ResizableSettings(80, 200, Align.topLeft).xOffset(220f).paddingY(10));

        getHand().setRotation(180f);
    }
}
