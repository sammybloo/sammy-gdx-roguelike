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

    public static final float WIDTH = 360f;
    public static final float HEIGHT = 540f;

    public BattlerCard(BattlerModel battlerModel) {
        super(battlerModel.getCardModel());
        this.battlerModel = battlerModel;
        setSize(WIDTH, HEIGHT);
        setOrigin(Align.center);
        art = new Image(AssetManager.INSTANCE.getSprite(battlerModel.getBattlerId()));
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

        addActor(art);
        addActor(battleFrame);
        addActor(nameLabel);
        addActor(powerLabel);
        addActor(healthLabel);

        layoutCard();
    }

    private void layoutCard() {
        float w = getWidth();
        float h = getHeight();

        float scaleX = w / WIDTH;
        float scaleY = h / HEIGHT;
        float fontScale = Math.min(scaleX, scaleY);

        battleFrame.setBounds(0, 0, w, h);

        art.setBounds(
            w * 0.025f,
            h * 0.284f,
            w * 0.948f,
            w * 0.948f
        );

        nameLabel.setFontScale(fontScale);
        nameLabel.setBounds(
            0,
            h * 0.916f,
            w,
            h * 0.07f
        );

        powerLabel.setFontScale(fontScale);
        powerLabel.setBounds(
            w * 0.0425f,
            h * 0.296f,
            w * 0.181f,
            h * 0.12f
        );


        healthLabel.setFontScale(fontScale);
        healthLabel.setBounds(
            w - (w * 0.181f) - (w * 0.0425f),
            h * 0.296f,
            w * 0.181f,
            h * 0.12f
        );
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        if (battleFrame != null) {
            layoutCard();
        }
    }
}
