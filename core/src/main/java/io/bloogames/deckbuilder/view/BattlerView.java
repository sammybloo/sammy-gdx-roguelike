package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.model.BattlerModel;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.View;
import io.bloogames.deckbuilder.ui.target.Targetable;
import io.bloogames.deckbuilder.ui.target.TargetingVisualState;

public class BattlerView extends ResizableGroup implements View, Targetable {
    public final static float WIDTH = 200;
    public final static float HEIGHT = 200;
    private final TargetingVisualState targetingVisualState = new TargetingVisualState();
    private final BattlerModel model;
    private final Image art;
    private final Image frame;
    private final Label powerLabel;
    private final Label healthLabel;

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

    public void playEntry() {
        addAction(Actions.fadeIn(0.2f));
        addAction(Actions.sequence(Actions.scaleTo(1.2f, 1.2f), Actions.scaleTo(1, 1, 0.2f)));
    }

    @Override
    public void sync() {
        powerLabel.setText(model.getPower());
        healthLabel.setText(model.getHealth());
    }

    @Override
    public void applyHighlight() {
        Color colour = targetingVisualState().getColour();
        art.setColor(colour);
        frame.setColor(colour);
        powerLabel.setColor(colour);
        healthLabel.setColor(colour);

        if (targetingVisualState().isHovered() && !targetingVisualState().isTargeted()) {
            addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
        } else {
            addAction(Actions.scaleTo(1f, 1f, 0.1f));
        }
    }

    @Override
    public TargetingVisualState targetingVisualState() {
        return targetingVisualState;
    }

    @Override
    public Actor actor() {
        return this;
    }
}
