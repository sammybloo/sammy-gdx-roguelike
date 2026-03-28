package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.awt.*;

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

    public void dispose() {
        if (atlas != null) {
            atlas.dispose();
        }
    }
}
