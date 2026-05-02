package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.model.BattlerModel;
import io.bloogames.deckbuilder.scene2d.IconGrid;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.scene2d.ResizableSettings;
import io.bloogames.deckbuilder.scene2d.UpdatingLabel;
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
    private final UpdatingLabel powerLabel;
    private final UpdatingLabel healthLabel;

    public BattlerView(BattlerModel model) {
        super(WIDTH, HEIGHT);
        this.setOrigin(Align.center);
        this.model = model;
        this.art = new Image(AssetManager.INSTANCE.findRegion("card/" + model.getBattlerId()));
        art.setTouchable(Touchable.disabled);
        frame = new Image(AssetManager.INSTANCE.findRegion("frame"));
        frame.setTouchable(Touchable.disabled);
        powerLabel = new UpdatingLabel(WIDTH * 0.19f, HEIGHT * 0.19f,model.getPower() + "",
            FontManager.INSTANCE.getBattlerStatFont());
        powerLabel.getLabel().setAlignment(Align.center, Align.center);

        healthLabel = new UpdatingLabel(WIDTH * 0.19f, HEIGHT * 0.19f, model.getCurrentHealth() + "",
            FontManager.INSTANCE.getBattlerStatFont());
        healthLabel.getLabel().setAlignment(Align.center, Align.center);

        this.register(art, new ResizableSettings(WIDTH * 0.99f, HEIGHT * 0.99f, Align.center));
        this.register(frame, new ResizableSettings(WIDTH, HEIGHT, Align.center));
        this.register(powerLabel, new ResizableSettings(WIDTH * 0.19f, HEIGHT * 0.19f)
            .offset(WIDTH * 0.0175f, HEIGHT * 0.0175f).keepColour());
        this.register(healthLabel, new ResizableSettings(WIDTH * 0.19f, HEIGHT * 0.19f, Align.bottomRight)
            .offset(WIDTH * 0.0175f, HEIGHT * 0.0175f).keepColour());

        addTint(targetingVisualState().getTint());

        sync();
    }

    public BattlerModel getModel() {
        return model;
    }

    @Override
    public void sync() {
        powerLabel.setText(model.getPower() + "");
        powerLabel.setColourByComparison(model.getStats().getBaseStats().power(), model.getPower());
        healthLabel.setText(model.getCurrentHealth() + "");
        if (model.getDamage() > 0) {
            healthLabel.setNegative();
        }
        else {
            healthLabel.setColourByComparison(model.getStats().getBaseStats().health(), model.getCurrentHealth());
        }
    }

    @Override
    public void applyHighlight() {
        targetingVisualState().updateTint();

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
