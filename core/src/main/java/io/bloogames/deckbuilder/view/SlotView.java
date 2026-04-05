package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.model.BattlerModel;
import io.bloogames.deckbuilder.model.SlotModel;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.View;

public class SlotView extends ResizableGroup implements View {
    private final Image image;
    private final SlotModel model;
    private BattlerView battler;

    public static float WIDTH = 250;
    public static float HEIGHT = 250;

    public SlotView(SlotModel model) {
        super(WIDTH, HEIGHT);
        this.model = model;
        image = new Image(AssetManager.INSTANCE.getSprite("slot"));
        register(image, new ResizeableSettings(WIDTH, HEIGHT));
        setBattler(model.getBattler());
    }

    public SlotModel getModel() {
        return model;
    }

    public BattlerView getBattler() {
        return battler;
    }

    public BattlerModel getBattlerModel() {
        return hasBattler() ? battler.getModel() : null;
    }

    public void setBattler(BattlerModel battlerModel) {
        if (getBattlerModel() == battlerModel) return;

        if (hasBattler()) {
            removeBattler();
        }

        if (battlerModel == null) {
            return;
        }

        this.battler = new BattlerView(battlerModel);
        register(battler, new ResizeableSettings(WIDTH * 0.8f, HEIGHT * 0.8f, Align.center).keepAspect());
    }

    public void setBattler(BattlerView battler) {
        if (this.battler == battler) return;

        if (hasBattler()) {
            removeBattler();
        }

        if (battler == null) {
            return;
        }

        this.battler = battler;
        register(battler, new ResizeableSettings(WIDTH * 0.8f, HEIGHT * 0.8f, Align.center).keepAspect());
    }

    public void removeBattler() {
        if (!hasBattler()) return;

        unregister(battler);
        this.battler = null;
    }

    public boolean hasBattler() {
        return battler != null;
    }

    public boolean hasBattler(BattlerModel other) {
        if (battler == null) {
            return other == null;
        }
        return battler.getModel() == other;
    }

    @Override
    public void update() {
        setBattler(model.getBattler());

        if (hasBattler()) {
            battler.update();
        }
    }
}
