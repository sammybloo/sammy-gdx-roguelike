package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.model.BattlerModel;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.HighlightState;
import io.bloogames.deckbuilder.ui.Highlightable;
import io.bloogames.deckbuilder.ui.View;

public class BattlerView extends ResizableGroup implements View, Highlightable {
    private final BattlerModel model;
    private final Image art;
    private final Image frame;
    private final Label powerLabel;
    private final Label healthLabel;

    public final static float WIDTH = 200;
    public final static float HEIGHT = 200;

    public BattlerView(BattlerModel model) {
        super(WIDTH, HEIGHT);
        this.setOrigin(Align.center);
        this.model = model;
        this.art = new Image(AssetManager.INSTANCE.getSprite("card/" + model.getBattlerId()));
        art.setTouchable(Touchable.disabled);
        frame = new Image(AssetManager.INSTANCE.getSprite("frame"));
        frame.setTouchable(Touchable.disabled);
        powerLabel = new Label("", new Label.LabelStyle(FontManager.INSTANCE.getBattlerStatFont(), null));
        powerLabel.setAlignment(Align.center, Align.center);
        powerLabel.setTouchable(Touchable.disabled);

        healthLabel = new Label("",
            new Label.LabelStyle(FontManager.INSTANCE.getBattlerStatFont(), null));
        healthLabel.setAlignment(Align.center, Align.center);
        healthLabel.setTouchable(Touchable.disabled);

        this.register(art, new ResizeableSettings(WIDTH, HEIGHT));
        this.register(frame, new ResizeableSettings(WIDTH, HEIGHT));
        this.register(powerLabel, new ResizeableSettings(WIDTH * 0.19f, HEIGHT * 0.19f)
            .offset(WIDTH * 0.0175f, HEIGHT * 0.0175f));
        this.register(healthLabel, new ResizeableSettings(WIDTH * 0.19f, HEIGHT * 0.19f, Align.bottomRight)
            .offset(WIDTH * 0.0175f, HEIGHT * 0.0175f));

        sync();
    }

    public BattlerModel getModel() {
        return model;
    }

    @Override
    public void sync() {
        powerLabel.setText(model.getPower());
        healthLabel.setText(model.getHealth());
    }

    public void setHighlightState(HighlightState state) {
        art.setColor(state.getColour());
        frame.setColor(state.getColour());
        //addAction(Actions.color(state.getColour()));
    }
}
