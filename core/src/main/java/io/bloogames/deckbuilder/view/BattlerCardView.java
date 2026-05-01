package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.model.BattlerCardModel;
import io.bloogames.deckbuilder.scene2d.ResizableSettings;
import io.bloogames.deckbuilder.scene2d.UpdatingLabel;

public class BattlerCardView extends CardView {
    public static final float WIDTH = CardView.WIDTH;
    public static final float HEIGHT = CardView.HEIGHT;
    private final BattlerCardModel battlerCardModel;

    private final UpdatingLabel powerLabel;
    private final UpdatingLabel healthLabel;

    public BattlerCardView(BattlerCardModel model) {
        super(model, "battlerframe", "card/" + model.getCardId());
        this.battlerCardModel = model;

        powerLabel = new UpdatingLabel(WIDTH * 0.181f, HEIGHT * 0.12f,
            battlerCardModel.getPower() + "", FontManager.INSTANCE.getBattlerCardStatFont());
        powerLabel.getLabel().setAlignment(Align.center, Align.center);

        healthLabel = new UpdatingLabel(WIDTH * 0.181f, HEIGHT * 0.12f,
            battlerCardModel.getHealth() + "", FontManager.INSTANCE.getBattlerCardStatFont());
        healthLabel.getLabel().setAlignment(Align.center, Align.center);

        frontFace.register(powerLabel, new ResizableSettings(powerLabel.getTargetWidth(), powerLabel.getTargetHeight())
            .offset(30f, HEIGHT * 0.296f).keepColour());
        frontFace.register(healthLabel, new ResizableSettings(healthLabel.getTargetWidth(), healthLabel.getTargetHeight(), Align.bottomRight)
            .offset(30f, HEIGHT * 0.296f));

        sync();
    }

    @Override
    public void sync() {
        super.sync();
        powerLabel.setText(battlerCardModel.getPower() + "");
        powerLabel.setColourByComparison(battlerCardModel.getBaseBattlerCard().getBaseStats().power(), battlerCardModel.getPower());
        healthLabel.setText(battlerCardModel.getHealth() + "");
        healthLabel.setColourByComparison(battlerCardModel.getBaseBattlerCard().getBaseStats().health(), battlerCardModel.getHealth());
    }

}
