package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import io.bloogames.deckbuilder.manager.AssetManager;

public class Slot extends Group {
    private Image image;
    private Participant participant;
    private Battler battler;

    public static float WIDTH = 250;
    public static float HEIGHT = 250;

    public Slot(Participant participant) {
        this.participant = participant;
        setSize(WIDTH, HEIGHT);
        image = new Image(AssetManager.INSTANCE.getSprite("slot"));
        image.setSize(WIDTH, HEIGHT);
        image.setColor(participant.getColour());
        addActor(image);
    }

    public Slot(Battler battler) {
        super();
        setBattler(battler);
    }

    public Battler getBattler() {
        return battler;
    }

    public void setBattler(Battler battler) {
        if (hasBattler()) {
            removeBattler();
        }
        if (battler == null) {
            return;
        }
        this.battler = battler;
        addActor(battler);
        battler.setPosition(getWidth() / 2 - battler.getWidth() / 2, getHeight() / 2 - battler.getHeight() / 2);
    }

    public void removeBattler() {
        if (hasBattler()) {
            removeActor(battler);
            this.battler = null;
        }
    }

    public boolean hasBattler() {
        return battler != null;
    }

    public boolean hasBattler(Battler other) {
        return battler == other;
    }
}
