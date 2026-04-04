package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.controller.EnemyHandController;
import io.bloogames.deckbuilder.controller.PlayerHandController;
import io.bloogames.deckbuilder.model.PartyModel;
import io.bloogames.deckbuilder.model.TableauModel;
import io.bloogames.deckbuilder.scene2d.FannedGroup;

public class EnemyParty extends Party {

    public EnemyParty(PartyModel model) {
        super(model);
        setLeader(new Leader(model.getLeader()));
        getLeader().setPosition(15, 15);

        setHand(new Hand(model.getHand(),
            new FannedGroup.FanSettings(0.3f, 0.3f, 10f, 1.5f,
                1.05f, 180f, 0.2f),
            new EnemyHandController()));
        getHand().setRotation(180f);
        setTableau(new Tableau(model.getTableau()));

        register(getLeader(), new ResizeableSettings(200f, 200f, Align.topLeft)
            .padding(10, 10).keepAspect());
        register(getTableau(), new ResizeableSettings(WIDTH * 0.8f, HEIGHT * 0.3f, Align.bottom)
            .yOffset(10f));
        register(getHand(), new ResizeableSettings(WIDTH, HEIGHT * 0.5f, Align.top).yOffset(HEIGHT * -0.3f));
    }
}
