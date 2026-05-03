package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.model.aura.AuraModel;
import io.bloogames.deckbuilder.ui.scene2d.HoverListener;
import io.bloogames.deckbuilder.ui.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.scene2d.ResizableSettings;

public class AuraView extends ResizableGroup {
    public final static float WIDTH = 40;
    public final static float HEIGHT = 40;

    private final Image image;

    public AuraView(AuraModel aura) {
        super(WIDTH, HEIGHT);
        image = new Image(AssetManager.INSTANCE.findRegion("aura/" + aura.getId()));
        register(image, new ResizableSettings(WIDTH, HEIGHT));

        addListener(new HoverListener(0, 0) {
            @Override
            public void onHoverStart(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                Gdx.app.log(AuraView.class.getSimpleName(), aura.description());
            }

            @Override
            public void onHoverEnd(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }
        });
    }
}
