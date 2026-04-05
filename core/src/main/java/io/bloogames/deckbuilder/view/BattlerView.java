package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.model.BattlerModel;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.View;

public class BattlerView extends ResizableGroup implements View {
    private BattlerModel model;
    private Image art;
    private Image frame;
    private Label powerLabel;
    private Label healthLabel;

    public final static float WIDTH = 200;
    public final static float HEIGHT = 200;

    public BattlerView(BattlerModel model) {
        super(WIDTH, HEIGHT);
        this.setOrigin(Align.center);
        this.model = model;
        this.art = new Image(AssetManager.INSTANCE.getSprite("card/" + model.getBattlerId()));
        frame = new Image(AssetManager.INSTANCE.getSprite("frame"));
        powerLabel = new Label("", new Label.LabelStyle(FontManager.INSTANCE.getBattlerStatFont(), null));
        powerLabel.setAlignment(Align.center, Align.center);
        healthLabel = new Label("",
            new Label.LabelStyle(FontManager.INSTANCE.getBattlerStatFont(), null));
        healthLabel.setAlignment(Align.center, Align.center);

        this.register(art, new ResizeableSettings(WIDTH, HEIGHT));
        this.register(frame, new ResizeableSettings(WIDTH, HEIGHT));
        this.register(powerLabel, new ResizeableSettings(WIDTH * 0.19f, HEIGHT * 0.19f)
            .offset(WIDTH * 0.0175f, HEIGHT * 0.0175f));
        this.register(healthLabel, new ResizeableSettings(WIDTH * 0.19f, HEIGHT * 0.19f, Align.bottomRight)
            .offset(WIDTH * 0.0175f, HEIGHT * 0.0175f));

        update();
    }

    public BattlerModel getModel() {
        return model;
    }

    @Override
    public void update() {
        powerLabel.setText(model.getPower());
        healthLabel.setText(model.getHealth());
    }
}
