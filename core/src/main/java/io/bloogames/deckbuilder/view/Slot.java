package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.model.SlotModel;

public class Slot extends Group {
    private final Image image;
    private final SlotModel model;
    private Battler battler;

    public static float WIDTH = 250;
    public static float HEIGHT = 250;

    public Slot(SlotModel model) {
        this.model = model;
        setSize(WIDTH, HEIGHT);
        image = new Image(AssetManager.INSTANCE.getSprite("slot"));
        image.setSize(WIDTH, HEIGHT);
        image.setColor(model.getParticipant().getColour());
        addActor(image);
    }

    public SlotModel getModel() {
        return model;
    }

    public Battler getBattler() {
        return battler;
    }

    public void setBattler(Battler battler) {
        if (this.battler == battler) return;

        if (hasBattler()) {
            removeBattler();
        }

        if (battler == null) {
            return;
        }

        this.battler = battler;
        resetBattler();
    }

    public void removeBattler() {
        if (!hasBattler()) return;

        removeActor(battler);
        this.battler = null;
    }

    public boolean hasBattler() {
        return battler != null;
    }

    public boolean hasBattler(Battler other) {
        return battler == other;
    }

    public void resetBattler() {
        addActor(battler);
        battler.setPosition(getWidth() / 2f - battler.getWidth() / 2f, getHeight() / 2f - battler.getHeight() / 2f);
    }
}
