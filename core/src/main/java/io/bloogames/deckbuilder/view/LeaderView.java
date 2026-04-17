package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.model.LeaderModel;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.View;
import io.bloogames.deckbuilder.ui.target.Targetable;
import io.bloogames.deckbuilder.ui.target.TargetingVisualState;

public class LeaderView extends ResizableGroup implements View, Targetable {
    private final TargetingVisualState targetingVisualState = new TargetingVisualState();
    private final LeaderModel model;
    private final Image image;
    private final Image frame;
    private final Label healthLabel;

    private static final float WIDTH = 200;
    private static final float HEIGHT = 200;

    public LeaderView(LeaderModel model) {
        super(WIDTH, HEIGHT);

        this.model = model;
        image = new Image(AssetManager.INSTANCE.getSprite("leader/" + model.getId()));
        image.setTouchable(Touchable.disabled);
        frame = new Image(AssetManager.INSTANCE.getSprite("leaderframe"));
        frame.setTouchable(Touchable.disabled);
        healthLabel = new Label(model.getMaxHealth() + "",
            new Label.LabelStyle(FontManager.INSTANCE.getLeaderHealthFont(), null));
        healthLabel.setTouchable(Touchable.disabled);
        healthLabel.setAlignment(Align.bottom);
        register(image, new ResizeableSettings(WIDTH, HEIGHT));
        register(frame, new ResizeableSettings(WIDTH, HEIGHT));
        register(healthLabel, new ResizeableSettings(40, 40, Align.bottom).yOffset(-6f));
    }

    public int getMaxHealth() {
        return model.getMaxHealth();
    }

    public LeaderModel getModel() {
        return model;
    }

    @Override
    public void sync() {
    }

    @Override
    public TargetingVisualState targetingVisualState() {
        return targetingVisualState;
    }

    @Override
    public void applyHighlight() {
        Color colour = targetingVisualState().getColour();
        image.setColor(colour);
        frame.setColor(colour);
        healthLabel.setColor(colour);
    }

    @Override public Actor actor() {
        return this;
    }
}
