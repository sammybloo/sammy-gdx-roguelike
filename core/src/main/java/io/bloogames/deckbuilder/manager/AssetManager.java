package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

public enum AssetManager {
    INSTANCE;

    TextureAtlas atlas;

    public TextureAtlas getAtlas() {
        if (atlas == null) {
            atlas = new TextureAtlas(Gdx.files.internal("pack/game.atlas"));
        }
        return atlas;
    }

    public TextureRegion findRegion(String name) {
        return getAtlas().findRegion(name);
    }

    public NinePatch getNinePatch(String name) {
        return getAtlas().createPatch(name);
    }

    public I18NBundle getBundle(String name, Locale locale) {
        FileHandle baseFileHandle = Gdx.files.internal("i18n/" + name);
        return I18NBundle.createBundle(baseFileHandle, locale);
    }

    public void dispose() {
        if (atlas != null) {
            atlas.dispose();
        }
    }
}
