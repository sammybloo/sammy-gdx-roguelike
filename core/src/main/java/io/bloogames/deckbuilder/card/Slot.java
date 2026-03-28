package io.bloogames.deckbuilder.card;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import io.bloogames.deckbuilder.manager.AssetManager;

public class Slot extends Group {
    Image image;
    Battler battler;

    public static float WIDTH = 350;
    public static float HEIGHT = 350;

    public Slot() {
        setSize(WIDTH, HEIGHT);
        image = new Image(AssetManager.INSTANCE.getSprite("slot"));
        image.setSize(WIDTH, HEIGHT);
        addActor(image);
    }

    public Slot(Battler battler) {
        super();
        setBattler(battler);
    }

    public void setBattler(Battler battler) {
        if (battler != null) {
            removeActor(battler);
        }
        this.battler = battler;
        addActor(battler);
        battler.setPosition(getWidth() / 2 - battler.getWidth() / 2, getHeight() / 2 - battler.getHeight() / 2);
    }
}
