package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.controller.BattleController;
import io.bloogames.deckbuilder.controller.HandController;
import io.bloogames.deckbuilder.controller.LeaderMessageController;
import io.bloogames.deckbuilder.controller.TableauController;
import io.bloogames.deckbuilder.handler.CardSelectHandler;
import io.bloogames.deckbuilder.handler.HandHoverHandler;
import io.bloogames.deckbuilder.model.BattlePartyModel;
import io.bloogames.deckbuilder.scene2d.FannedGroup;
import io.bloogames.deckbuilder.scene2d.ResizableSettings;
import io.bloogames.deckbuilder.view.event.ViewEvent;

public class PlayerPartyView extends PartyView {

    LeaderMessageView leaderMessageView;

    public PlayerPartyView(BattleController battleController, BattlePartyModel model) {
        super(model);
        setLeader(new LeaderView(model.getLeader()));

        setHand(new HandView(model.getHand(),
            new FannedGroup.FanSettings(0.3f, 0.5f, 16f, 1.5f,
                0.8f, 0f, 0.15f),
            new HandHoverHandler(battleController.getBattleState(), 0, 0.1f),
            new CardSelectHandler(battleController, model.getParty())));
        new HandController(getHand(), battleController, model.getParty());

        setTableau(new TableauView(model.getTableau()));
        new TableauController(getTableau(), battleController, model.getParty(), true);

        setManaView(new ManaView(model.getLeader()));

        register(getTableau(), new ResizableSettings(WIDTH * 0.6f, HEIGHT * 0.4f, Align.top)
            .yOffset(10f));
        register(getHand(), new ResizableSettings(WIDTH * 0.66f, HEIGHT * 0.5f, Align.bottom).yOffset(HEIGHT * -0.3f));

        register(getLeader(), new ResizableSettings(200f, 200f).padding(10, 10).keepAspect());
        leaderMessageView = new LeaderMessageView();
        register(getManaView(), new ResizableSettings(80, 200).xOffset(220f).paddingY(10));
        battleController.getEventBus().register(ViewEvent.ManaSpentEvent.class, e -> getManaView().sync());
        register(leaderMessageView, new ResizableSettings(600, 200).offset(220f, 110f));
        new LeaderMessageController(leaderMessageView, battleController);

    }
}
