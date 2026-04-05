package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.model.LeaderModel;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.View;

public class LeaderView extends ResizableGroup implements View {
    private LeaderModel model;
    private Image image;
    private Image frame;
    private Label healthLabel;

    private static float WIDTH = 200;
    private static float HEIGHT = 200;

    public LeaderView(LeaderModel model) {
        super(WIDTH, HEIGHT);

        this.model = model;
        this.image = new Image(AssetManager.INSTANCE.getSprite("leader/" + model.getId()));
        this.frame = new Image(AssetManager.INSTANCE.getSprite("leaderframe"));
        this.healthLabel = new Label(model.getMaxHealth() + "",
            new Label.LabelStyle(FontManager.INSTANCE.getLeaderHealthFont(), null));
        healthLabel.setAlignment(Align.bottom);
        register(image, new ResizeableSettings(WIDTH, HEIGHT));
        register(frame, new ResizeableSettings(WIDTH, HEIGHT));
        register(healthLabel, new ResizeableSettings(40, 40, Align.bottom).yOffset(2f));
    }

    public int getMaxHealth() {
        return model.getMaxHealth();
    }

    public LeaderModel getModel() {
        return model;
    }

    @Override
    public void update() {
    }
}
