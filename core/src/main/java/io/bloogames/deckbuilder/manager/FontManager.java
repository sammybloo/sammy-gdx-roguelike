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
    BitmapFont cardManaCostFont;
    BitmapFont damagePopupFont;
    BitmapFont deckCardsLeftFont;
    BitmapFont discardPileSizeFont;

    public BitmapFont getBattlerStatFont() {
        if (battlerStatFont == null) {
            var generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
            var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.color = Color.GRAY;
            parameter.size = 42;
            parameter.spaceX = -2;
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

    public BitmapFont getCardManaCostFont() {
        if (cardManaCostFont == null) {
            var generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
            var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.color = Color.BLACK;
            parameter.size = 64;
            parameter.minFilter = Texture.TextureFilter.Linear;
            parameter.magFilter = Texture.TextureFilter.Linear;
            cardManaCostFont = generator.generateFont(parameter);
            generator.dispose();
        }

        return cardManaCostFont;
    }

    public BitmapFont getLeaderHealthFont() {
        if (leaderHealthFont == null) {
            var generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
            var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.color = Color.GRAY;
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

    public BitmapFont getDamagePopupFont() {
        if (damagePopupFont == null) {
            var generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
            var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.color = Color.WHITE;
            parameter.size = 64;
            parameter.minFilter = Texture.TextureFilter.Linear;
            parameter.magFilter = Texture.TextureFilter.Linear;
            parameter.borderColor = Color.BLACK;
            parameter.borderWidth = 3;
            damagePopupFont = generator.generateFont(parameter);
            generator.dispose();
        }

        return damagePopupFont;
    }

    public BitmapFont getDeckCardsLeftFont() {
        if (deckCardsLeftFont == null) {
            var generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
            var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.color = Color.WHITE;
            parameter.size = 76;
            parameter.minFilter = Texture.TextureFilter.Linear;
            parameter.magFilter = Texture.TextureFilter.Linear;
            parameter.borderColor = Color.BLACK;
            parameter.borderWidth = 3;
            deckCardsLeftFont = generator.generateFont(parameter);
            generator.dispose();
        }

        return deckCardsLeftFont;
    }

    public BitmapFont getDiscardPileSizeFont() {
        if (discardPileSizeFont == null) {
            var generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
            var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.color = Color.WHITE;
            parameter.size = 76;
            parameter.minFilter = Texture.TextureFilter.Linear;
            parameter.magFilter = Texture.TextureFilter.Linear;
            parameter.borderColor = Color.BLACK;
            parameter.borderWidth = 3;
            discardPileSizeFont = generator.generateFont(parameter);
            generator.dispose();
        }

        return discardPileSizeFont;
    }

    public void dispose() {
        if (cardNameFont != null) cardNameFont.dispose();
        if (battlerStatFont != null) battlerStatFont.dispose();
        if (battlerCardStatFont != null) battlerCardStatFont.dispose();
        if (leaderHealthFont != null) leaderHealthFont.dispose();
        if (leaderMessageFont != null) leaderMessageFont.dispose();
        if (damagePopupFont != null) damagePopupFont.dispose();
        if (deckCardsLeftFont != null) deckCardsLeftFont.dispose();
        if (discardPileSizeFont != null) discardPileSizeFont.dispose();
    }
}
