package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.controller.BattleController;
import io.bloogames.deckbuilder.handler.CardSelectHandler;
import io.bloogames.deckbuilder.handler.HandHoverHandler;
import io.bloogames.deckbuilder.model.BattlePartyModel;
import io.bloogames.deckbuilder.scene2d.FannedGroup;
import io.bloogames.deckbuilder.scene2d.ResizableSettings;

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

        setTableau(new TableauView(model.getTableau()));

        setManaView(new ManaView(model.getLeader()));

        setDeck(new DeckView(model.getDeck(), false));

        register(getTableau(), new ResizableSettings(WIDTH * 0.6f, HEIGHT * 0.4f, Align.top)
            .yOffset(10f));

        register(getLeader(), new ResizableSettings(200f, 200f).padding(10, 10).keepAspect());

        register(getDeck(), new ResizableSettings(200, 300, Align.topRight).offset(10, 10).keepAspect());

        register(getHand(), new ResizableSettings(WIDTH * 0.66f, HEIGHT * 0.5f, Align.bottom).yOffset(HEIGHT * -0.3f));

        register(getManaView(), new ResizableSettings(80, 200).xOffset(220f).paddingY(10).keepAspect());

        leaderMessageView = new LeaderMessageView();
        register(leaderMessageView, new ResizableSettings(600, 200).offset(220f, 110f));
    }

    public LeaderMessageView getLeaderMessageView() {
        return leaderMessageView;
    }
}
