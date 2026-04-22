package io.bloogames.deckbuilder.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.AssetManager;

public class Crosshair extends Group {
    public static float WIDTH = 75;
    public static float HEIGHT = 75;
    Image image;
    Image shadow;
    Vector2 mousePosition = new Vector2();

    public Crosshair() {
        setTouchable(Touchable.disabled);
        setSize(WIDTH, HEIGHT);
        setOrigin(Align.center);
        image = new Image(AssetManager.INSTANCE.findRegion("crosshair"));
        image.setSize(WIDTH, HEIGHT);
        image.setColor(Color.RED);
        shadow = new Image(AssetManager.INSTANCE.findRegion("crosshair"));
        shadow.setSize(WIDTH, HEIGHT);
        shadow.setColor(new Color(0, 0, 0, 0.25f));
        shadow.setPosition(1.5f, -1.5f);
        addActor(shadow);
        addActor(image);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (getStage() == null) return;

        mousePosition.set(Gdx.input.getX(), Gdx.input.getY());
        getStage().screenToStageCoordinates(mousePosition);

        setPosition(mousePosition.x - getWidth() / 2, mousePosition.y - getHeight() / 2);
    }
}
