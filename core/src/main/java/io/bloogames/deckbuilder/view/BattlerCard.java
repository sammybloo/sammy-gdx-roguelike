package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.model.BattlerModel;

public class BattlerCard extends Card {
    private BattlerModel battlerModel;
    private Image battleFrame;
    private Image art;
    private Label nameLabel;
    private Label powerLabel;
    private Label healthLabel;

    public static final float WIDTH = Card.WIDTH;
    public static final float HEIGHT = Card.HEIGHT;

    public BattlerCard(BattlerModel battlerModel) {
        super(battlerModel.getCardModel());
        this.battlerModel = battlerModel;
        setOrigin(Align.center);
        art = new Image(AssetManager.INSTANCE.getSprite("card/" + battlerModel.getBattlerId()));
        battleFrame = new Image(AssetManager.INSTANCE.getSprite("battlerframe"));

        nameLabel = new Label(battlerModel.getCardModel().getCardName(),
            new Label.LabelStyle(FontManager.INSTANCE.getCardNameFont(), null));
        nameLabel.setTouchable(Touchable.disabled);
        nameLabel.setAlignment(Align.center, Align.center);

        powerLabel = new Label(battlerModel.getPower() + "",
            new Label.LabelStyle(FontManager.INSTANCE.getBattlerCardStatFont(), null));
        powerLabel.setTouchable(Touchable.disabled);
        powerLabel.setAlignment(Align.center, Align.center);

        healthLabel = new Label(battlerModel.getHealth() + "",
            new Label.LabelStyle(FontManager.INSTANCE.getBattlerCardStatFont(), null));
        healthLabel.setTouchable(Touchable.disabled);
        healthLabel.setAlignment(Align.center, Align.center);

        showContents();
    }

    @Override
    public void hideContents() {
        unregister(art);
        unregister(battleFrame);
        unregister(nameLabel);
        unregister(powerLabel);
        unregister(healthLabel);
    }

    @Override
    public void showContents() {
        register(art, new ResizeableSettings(WIDTH * 0.948f, WIDTH * 0.948f).offset(WIDTH * 0.025f, HEIGHT * 0.284f));
        register(battleFrame, new ResizeableSettings(WIDTH, HEIGHT, Align.center));
        register(nameLabel, new ResizeableSettings(WIDTH, 25, Align.top).yOffset(15f));
        register(powerLabel, new ResizeableSettings(WIDTH * 0.181f, HEIGHT * 0.12f).offset(30f, HEIGHT * 0.296f));
        register(healthLabel, new ResizeableSettings(WIDTH * 0.181f, HEIGHT * 0.12f, Align.bottomRight).offset(30f, HEIGHT * 0.296f));
    }
}
