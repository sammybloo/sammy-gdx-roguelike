package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.model.LeaderModel;
import io.bloogames.deckbuilder.ui.View;
import io.bloogames.deckbuilder.ui.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.scene2d.ResizableSettings;
import io.bloogames.deckbuilder.ui.scene2d.UpdatingLabel;
import io.bloogames.deckbuilder.ui.target.Targetable;
import io.bloogames.deckbuilder.ui.target.TargetingVisualState;

public class LeaderView extends ResizableGroup implements View, Targetable {
    private static final float WIDTH = 200;
    private static final float HEIGHT = 200;
    private final TargetingVisualState targetingVisualState = new TargetingVisualState();
    private final LeaderModel model;
    private final Image image;
    private final Image frame;
    private final UpdatingLabel healthLabel;

    public LeaderView(LeaderModel model) {
        super(WIDTH, HEIGHT);

        this.model = model;
        image = new Image(AssetManager.INSTANCE.findRegion("leader/" + model.getId()));
        image.setTouchable(Touchable.disabled);
        frame = new Image(AssetManager.INSTANCE.findRegion("leaderframe"));
        frame.setTouchable(Touchable.disabled);
        healthLabel = new UpdatingLabel(40, 40, model.getCurrentHealth() + "",
            FontManager.INSTANCE.getLeaderHealthFont());
        healthLabel.getLabel().setAlignment(Align.bottom);
        register(image, new ResizableSettings(WIDTH, HEIGHT));
        register(frame, new ResizableSettings(WIDTH, HEIGHT));
        register(healthLabel, new ResizableSettings(40, 40, Align.bottom).yOffset(-6f).keepColour());

        addTint(targetingVisualState().getTint());
    }

    public LeaderModel getModel() {
        return model;
    }

    @Override
    public void sync() {
        healthLabel.setText(model.getCurrentHealth() + "");
        healthLabel.setColourByComparison(model.getMaxHealth(), model.getCurrentHealth());
    }

    @Override
    public TargetingVisualState targetingVisualState() {
        return targetingVisualState;
    }

    @Override
    public void applyHighlight() {
        targetingVisualState.updateTint();
    }

    @Override
    public Actor actor() {
        return this;
    }
}
