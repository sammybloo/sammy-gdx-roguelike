package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.model.BattlerCardModel;
import io.bloogames.deckbuilder.ui.HighlightState;

public class BattlerCardView extends CardView {
    private BattlerCardModel battlerCardModel;
    private Image frame;
    private Image art;
    private Label nameLabel;
    private Label powerLabel;
    private Label healthLabel;

    public static final float WIDTH = CardView.WIDTH;
    public static final float HEIGHT = CardView.HEIGHT;

    public BattlerCardView(BattlerCardModel model) {
        super(model);
        this.battlerCardModel = model;
        setOrigin(Align.center);
        setTouchable(Touchable.enabled);
        art = new Image(AssetManager.INSTANCE.getSprite("card/" + model.getCardId()));
        art.setTouchable(Touchable.disabled);

        frame = new Image(AssetManager.INSTANCE.getSprite("battlerframe"));
        frame.setTouchable(Touchable.disabled);

        nameLabel = new Label(battlerCardModel.getCardName(),
            new Label.LabelStyle(FontManager.INSTANCE.getCardNameFont(), null));
        nameLabel.setTouchable(Touchable.disabled);
        nameLabel.setAlignment(Align.center, Align.center);

        powerLabel = new Label(battlerCardModel.getPower() + "",
            new Label.LabelStyle(FontManager.INSTANCE.getBattlerCardStatFont(), null));
        powerLabel.setTouchable(Touchable.disabled);
        powerLabel.setAlignment(Align.center, Align.center);

        healthLabel = new Label(battlerCardModel.getHealth() + "",
            new Label.LabelStyle(FontManager.INSTANCE.getBattlerCardStatFont(), null));
        healthLabel.setTouchable(Touchable.disabled);
        healthLabel.setAlignment(Align.center, Align.center);

        showContents();
    }

    @Override
    public void hideContents() {
        unregister(art);
        unregister(frame);
        unregister(nameLabel);
        unregister(powerLabel);
        unregister(healthLabel);
    }

    @Override
    public void showContents() {
        register(art, new ResizeableSettings(WIDTH * 0.948f, WIDTH * 0.948f).offset(WIDTH * 0.025f, HEIGHT * 0.284f));
        register(frame, new ResizeableSettings(WIDTH, HEIGHT, Align.center));
        register(nameLabel, new ResizeableSettings(WIDTH, 25, Align.top).yOffset(15f));
        register(powerLabel, new ResizeableSettings(WIDTH * 0.181f, HEIGHT * 0.12f).offset(30f, HEIGHT * 0.296f));
        register(healthLabel, new ResizeableSettings(WIDTH * 0.181f, HEIGHT * 0.12f, Align.bottomRight).offset(30f, HEIGHT * 0.296f));
    }

    @Override
    public void update() {
        nameLabel.setText(getName());
        powerLabel.setText(battlerCardModel.getPower());
        healthLabel.setText(battlerCardModel.getHealth());
    }

    @Override
    public void setHighlightState(HighlightState state) {
        super.setHighlightState(state);
        art.setColor(state.getColour());
        frame.setColor(state.getColour());
    }
}
