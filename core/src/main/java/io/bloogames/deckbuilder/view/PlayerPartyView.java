package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.controller.CardSelectController;
import io.bloogames.deckbuilder.controller.HandHoverController;
import io.bloogames.deckbuilder.model.PartyModel;
import io.bloogames.deckbuilder.model.coordinator.BattleCoordinator;
import io.bloogames.deckbuilder.scene2d.FannedGroup;

public class PlayerPartyView extends PartyView {

    public PlayerPartyView(BattleCoordinator coordinator, PartyModel model) {
        super(model);
        setLeader(new LeaderView(model.getLeader()));

        setHand(new HandView(model.getHand(),
            new FannedGroup.FanSettings(0.3f, 0.5f, 16f, 1.5f,
                0.7f, 0f, 0.3f),
            new HandHoverController(coordinator, 0, 0.1f),
            new CardSelectController(coordinator, model)));
        setTableau(new TableauView(model.getTableau()));

        register(getLeader(), new ResizeableSettings(200f, 200f).padding(10, 10).keepAspect());
        register(getTableau(), new ResizeableSettings(WIDTH * 0.6f, HEIGHT * 0.4f, Align.top)
            .yOffset(10f));
        register(getHand(), new ResizeableSettings(WIDTH, HEIGHT * 0.5f, Align.bottom).yOffset(HEIGHT * -0.3f));
    }
}
