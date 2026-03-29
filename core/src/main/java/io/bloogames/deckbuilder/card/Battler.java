package io.bloogames.deckbuilder.card;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.manager.FontManager;

public class Battler extends Group {
    private BattlerModel model;
    private Image sprite;
    private Image frame;
    private Label powerLabel;
    private Label healthLabel;

    public final static float WIDTH = 260;
    public final static float HEIGHT = 260;

    public Battler(BattlerModel model) {
        this.setSize(WIDTH,HEIGHT);
        this.setOrigin(Align.center);
        this.model = model;
        sprite = new Image(AssetManager.INSTANCE.getSprite(model.getBattlerId()));
        sprite.setSize(WIDTH, HEIGHT);

        frame = new Image(AssetManager.INSTANCE.getSprite("frame"));
        frame.setSize(WIDTH, HEIGHT);

        powerLabel = new Label(model.getPower() + "",
            new Label.LabelStyle(FontManager.INSTANCE.getBattlerStatFont(), null));

        powerLabel.setAlignment(Align.center, Align.center);
        powerLabel.setBounds(WIDTH * 0.0175f, HEIGHT * 0.0175f, WIDTH * 0.19f, HEIGHT * 0.19f);

        healthLabel = new Label(model.getHealth() + "",
            new Label.LabelStyle(FontManager.INSTANCE.getBattlerStatFont(), null));

        healthLabel.setAlignment(Align.center, Align.center);
        healthLabel.setBounds(WIDTH  - (WIDTH * 0.19f) - (WIDTH * 0.0175f),
            HEIGHT * 0.0175f, WIDTH * 0.19f, HEIGHT * 0.19f);
        this.addActor(sprite);
        this.addActor(frame);
        this.addActor(powerLabel);
        this.addActor(healthLabel);
    }
}
