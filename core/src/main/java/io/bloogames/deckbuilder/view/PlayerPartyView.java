package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.controller.HandController;
import io.bloogames.deckbuilder.controller.TableauController;
import io.bloogames.deckbuilder.handler.CardSelectHandler;
import io.bloogames.deckbuilder.handler.HandHoverHandler;
import io.bloogames.deckbuilder.handler.TableauSwapHandler;
import io.bloogames.deckbuilder.model.BattlePartyModel;
import io.bloogames.deckbuilder.controller.BattleController;
import io.bloogames.deckbuilder.scene2d.FannedGroup;

public class PlayerPartyView extends PartyView {

    public PlayerPartyView(BattleController battleController, BattlePartyModel model) {
        super(model);
        setLeader(new LeaderView(model.getLeader()));

        setHand(new HandView(model.getHand(),
            new FannedGroup.FanSettings(0.3f, 0.5f, 16f, 1.5f,
                0.7f, 0f, 0.3f),
            new HandHoverHandler(battleController.getBattleState(), 0, 0.1f),
            new CardSelectHandler(battleController, model)));
        new HandController(getHand(), battleController);

        setTableau(new TableauView(model.getTableau()));
        new TableauController(getTableau(), battleController, true);

        register(getLeader(), new ResizeableSettings(200f, 200f).padding(10, 10).keepAspect());
        register(getTableau(), new ResizeableSettings(WIDTH * 0.6f, HEIGHT * 0.4f, Align.top)
            .yOffset(10f));
        register(getHand(), new ResizeableSettings(WIDTH * 0.66f, HEIGHT * 0.5f, Align.bottom).yOffset(HEIGHT * -0.3f));
    }
}
