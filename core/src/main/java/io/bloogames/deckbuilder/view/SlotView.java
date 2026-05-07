package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.model.BattlerModel;
import io.bloogames.deckbuilder.model.SlotModel;
import io.bloogames.deckbuilder.model.aura.AuraModel;
import io.bloogames.deckbuilder.model.ownership.Ownership;
import io.bloogames.deckbuilder.ui.View;
import io.bloogames.deckbuilder.ui.scene2d.IconGrid;
import io.bloogames.deckbuilder.ui.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.scene2d.ResizableSettings;
import io.bloogames.deckbuilder.ui.target.Targetable;
import io.bloogames.deckbuilder.ui.target.TargetingVisualState;

public class SlotView extends ResizableGroup implements View, Targetable {
    public static float WIDTH = 250;
    public static float HEIGHT = 250;
    private final TargetingVisualState targetingVisualState = new TargetingVisualState();
    private final Image image;
    private final SlotModel model;
    private final AuraSetView aurasView;
    private BattlerView battler;

    public SlotView(SlotModel model) {
        super(WIDTH, HEIGHT);
        this.model = model;
        image = new Image(AssetManager.INSTANCE.findRegion("slot"));
        setTouchable(Touchable.childrenOnly);
        register(image, new ResizableSettings(WIDTH, HEIGHT));
        setBattler(model.getBattler());

        addTint(targetingVisualState().getTint());

        ResizableSettings aurasViewSettings = new ResizableSettings(WIDTH, HEIGHT / 3);

        if (model.getOwnership().getCurrentOwner() == Ownership.Type.PLAYER) {
            aurasView = new AuraSetView(IconGrid.VerticalAlign.TOP_TO_BOTTOM);
            aurasViewSettings.alignment(Align.bottom).yOffset(-HEIGHT / 3).paddingY(-1f);
        } else {
            aurasView = new AuraSetView(IconGrid.VerticalAlign.BOTTOM_TO_TOP);
            aurasViewSettings.alignment(Align.top).yOffset(-HEIGHT / 3).paddingY(-1f);
        }
        this.register(aurasView, aurasViewSettings);
    }

    public SlotModel getModel() {
        return model;
    }

    public BattlerView getBattler() {
        return battler;
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
        register(battler, new ResizableSettings(WIDTH * 0.8f, HEIGHT * 0.8f, Align.center).keepAspect().keepColour());
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
        register(battler, new ResizableSettings(WIDTH * 0.8f, HEIGHT * 0.8f, Align.center)
            .keepAspect().keepColour());
    }

    public BattlerModel getBattlerModel() {
        return hasBattler() ? battler.getModel() : null;
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
    public void sync() {
        setBattler(model.getBattler());

        if (hasBattler()) {
            battler.sync();
        }
        Array<AuraModel> auras = new Array<>();
        model.addAllAuras(auras);
        aurasView.addAuras(auras);
    }

    @Override
    public TargetingVisualState targetingVisualState() {
        return targetingVisualState;
    }

    @Override
    public void applyHighlight() {
        targetingVisualState().updateTint();
    }

    @Override
    public Actor actor() {
        return this;
    }
}
