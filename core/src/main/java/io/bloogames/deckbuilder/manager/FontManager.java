package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.awt.*;

public enum FontManager {
    INSTANCE;

    BitmapFont battlerStatFont;

    public BitmapFont getBattlerStatFont() {
        if (battlerStatFont == null) {
            var generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
            var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.color = Color.BLACK;
            parameter.size = 64;
            parameter.minFilter = Texture.TextureFilter.Linear;
            parameter.magFilter = Texture.TextureFilter.Linear;
            battlerStatFont = generator.generateFont(parameter);
            generator.dispose();
        }

        return battlerStatFont;
    }
}
