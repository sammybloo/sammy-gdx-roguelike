package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public enum FontManager {
    INSTANCE;

    BitmapFont cardNameFont;
    BitmapFont battlerStatFont;
    BitmapFont battlerCardStatFont;
    BitmapFont leaderHealthFont;
    BitmapFont leaderMessageFont;

    public BitmapFont getBattlerStatFont() {
        if (battlerStatFont == null) {
            var generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
            var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.color = Color.BLACK;
            parameter.size = 48;
            parameter.minFilter = Texture.TextureFilter.Linear;
            parameter.magFilter = Texture.TextureFilter.Linear;
            battlerStatFont = generator.generateFont(parameter);
            generator.dispose();
        }

        return battlerStatFont;
    }

    public BitmapFont getCardNameFont() {
        if (cardNameFont == null) {
            var generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
            var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.color = Color.BLACK;
            parameter.size = 32;
            parameter.minFilter = Texture.TextureFilter.Linear;
            parameter.magFilter = Texture.TextureFilter.Linear;
            cardNameFont = generator.generateFont(parameter);
            generator.dispose();
        }

        return cardNameFont;
    }

    public BitmapFont getBattlerCardStatFont() {
        if (battlerCardStatFont == null) {
            var generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
            var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.color = Color.BLACK;
            parameter.size = 64;
            parameter.minFilter = Texture.TextureFilter.Linear;
            parameter.magFilter = Texture.TextureFilter.Linear;
            battlerCardStatFont = generator.generateFont(parameter);
            generator.dispose();
        }

        return battlerCardStatFont;
    }

    public BitmapFont getLeaderHealthFont() {
        if (leaderHealthFont == null) {
            var generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
            var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.color = Color.BLACK;
            parameter.size = 48;
            parameter.minFilter = Texture.TextureFilter.Linear;
            parameter.magFilter = Texture.TextureFilter.Linear;
            leaderHealthFont = generator.generateFont(parameter);
            generator.dispose();
        }

        return leaderHealthFont;
    }

    public BitmapFont getLeaderMessageFont() {
        if (leaderMessageFont == null) {
            var generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
            var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.color = Color.BLACK;
            parameter.size = 36;
            parameter.minFilter = Texture.TextureFilter.Linear;
            parameter.magFilter = Texture.TextureFilter.Linear;
            leaderMessageFont = generator.generateFont(parameter);
            generator.dispose();
        }

        return leaderMessageFont;
    }

    public void dispose() {
        if (cardNameFont != null) cardNameFont.dispose();
        if (battlerStatFont != null) battlerStatFont.dispose();
        if (battlerCardStatFont != null) battlerCardStatFont.dispose();
    }
}
