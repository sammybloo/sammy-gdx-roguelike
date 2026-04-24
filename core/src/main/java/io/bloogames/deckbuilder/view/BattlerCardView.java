package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.model.BattlerCardModel;
import io.bloogames.deckbuilder.scene2d.ResizableSettings;

public class BattlerCardView extends CardView {
    public static final float WIDTH = CardView.WIDTH;
    public static final float HEIGHT = CardView.HEIGHT;
    private final BattlerCardModel battlerCardModel;

    private final Label powerLabel;
    private final Label healthLabel;

    public BattlerCardView(BattlerCardModel model) {
        super(model, "battlerframe", "card/" + model.getCardId());
        this.battlerCardModel = model;

        powerLabel = new Label(battlerCardModel.getPower() + "",
            new Label.LabelStyle(FontManager.INSTANCE.getBattlerCardStatFont(), null));
        powerLabel.setAlignment(Align.center, Align.center);

        healthLabel = new Label(battlerCardModel.getHealth() + "",
            new Label.LabelStyle(FontManager.INSTANCE.getBattlerCardStatFont(), null));
        healthLabel.setAlignment(Align.center, Align.center);

        frontFace.register(powerLabel, new ResizableSettings(WIDTH * 0.181f, HEIGHT * 0.12f).offset(30f, HEIGHT * 0.296f));
        frontFace.register(healthLabel, new ResizableSettings(WIDTH * 0.181f, HEIGHT * 0.12f, Align.bottomRight).offset(30f, HEIGHT * 0.296f));

        sync();
    }

    @Override
    public void sync() {
        super.sync();
        powerLabel.setText(battlerCardModel.getPower());
        healthLabel.setText(battlerCardModel.getHealth());
    }

}
