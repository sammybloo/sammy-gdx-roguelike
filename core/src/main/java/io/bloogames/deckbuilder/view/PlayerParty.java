package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.controller.PlayerHandController;
import io.bloogames.deckbuilder.model.PartyModel;
import io.bloogames.deckbuilder.model.TableauModel;
import io.bloogames.deckbuilder.scene2d.FannedGroup;

public class PlayerParty extends Party {

    public PlayerParty(PartyModel model) {
        super(model);
        setLeader(new Leader(model.getLeader()));

        setHand(new Hand(model.getHand(),
            new FannedGroup.FanSettings(0.3f, 0.5f, 16f, 1.5f,
                0.7f, 0f, 0.3f),
            new PlayerHandController()));
        setTableau(new Tableau(model.getTableau()));

        register(getLeader(), new ResizeableSettings(200f, 200f).padding(10, 10).keepAspect());
        register(getTableau(), new ResizeableSettings(WIDTH * 0.8f, HEIGHT * 0.3f, Align.top)
            .yOffset(10f));
        register(getHand(), new ResizeableSettings(WIDTH, HEIGHT * 0.5f, Align.bottom).yOffset(HEIGHT * -0.3f));
    }
}
