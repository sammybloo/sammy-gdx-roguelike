package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.model.aura.AuraModel;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.scene2d.ResizableSettings;

public class AuraView extends ResizableGroup {
    public final static float WIDTH = 40;
    public final static float HEIGHT = 40;

    private final Image image;

    public AuraView(AuraModel aura) {
        super(WIDTH, HEIGHT);
        image = new Image(AssetManager.INSTANCE.findRegion("aura/" + aura.getId()));
        register(image, new ResizableSettings(WIDTH, HEIGHT));
    }
}
