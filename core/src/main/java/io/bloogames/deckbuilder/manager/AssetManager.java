package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public enum AssetManager {
    INSTANCE;

    TextureAtlas atlas;

    public TextureAtlas getAtlas() {
        if (atlas == null) {
            atlas = new TextureAtlas(Gdx.files.internal("pack/game.atlas"));
        }
        return atlas;
    }

    public Sprite getSprite(String name) {
        return getAtlas().createSprite(name);
    }

    public NinePatch getNinePatch(String name) {
        return getAtlas().createPatch(name);
    }

    public Animation<TextureRegion> getAnimation(String region) {
        return new Animation<TextureRegion>(0.033f, atlas.findRegions("rem/gif"), Animation.PlayMode.LOOP);
    }

    public void dispose() {
        if (atlas != null) {
            atlas.dispose();
        }
    }
}
