package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ObjectMap;

public enum FontManager {
    INSTANCE;

    private static final int BATTLER_STAT_CODE = "BATTLER_STAT_CODE".hashCode();
    private static final int CARD_NAME_CODE = "CARD_NAME_CODE".hashCode();
    private static final int CARD_TEXT_CODE = "CARD_TEXT_CODE".hashCode();
    private static final int BATTLER_CARD_STAT_CODE = "BATTLER_CARD_STAT_CODE".hashCode();
    private static final int CARD_MANA_COST_CODE = "CARD_MANA_COST_CODE".hashCode();
    private static final int LEADER_HEALTH_CODE = "LEADER_HEALTH_CODE".hashCode();
    private static final int LEADER_MESSAGE_CODE = "LEADER_MESSAGE_CODE".hashCode();
    private static final int BUTTON_CODE = "BUTTON_CODE".hashCode();
    private static final int DAMAGE_POPUP_CODE = "DAMAGE_POPUP_CODE".hashCode();
    private static final int DECK_CARDS_LEFT_CODE = "DECK_CARDS_LEFT_CODE".hashCode();
    private static final int DISCARD_PILE_SIZE_CODE = "DISCARD_PILE_SIZE_CODE".hashCode();
    private final ObjectMap<Integer, BitmapFont> fonts;
    private final FreeTypeFontGenerator arialGenerator;

    FontManager() {
        fonts = new ObjectMap<>();
        arialGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
        Colors.put("GOOD", new Color(0f, 0.8f, 0f, 1f));
        Colors.put("BAD", new Color(0.8f, 0f, 0f, 1f));
    }

    private FreeTypeFontGenerator.FreeTypeFontParameter getBaseParameters(int size, Color color) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = color;
        parameter.size = size;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;
        return parameter;
    }

    public BitmapFont getBattlerStatFont() {
        if (!fonts.containsKey(BATTLER_STAT_CODE)) {
            var parameter = getBaseParameters(42, Color.GRAY);
            parameter.spaceX = -2;
            fonts.put(BATTLER_STAT_CODE, arialGenerator.generateFont(parameter));
        }
        return fonts.get(BATTLER_STAT_CODE);
    }

    public BitmapFont getCardNameFont() {
        if (!fonts.containsKey(CARD_NAME_CODE)) {
            var parameter = getBaseParameters(32, Color.BLACK);
            fonts.put(CARD_NAME_CODE, arialGenerator.generateFont(parameter));
        }
        return fonts.get(CARD_NAME_CODE);
    }

    public BitmapFont getCardTextFont() {
        if (!fonts.containsKey(CARD_TEXT_CODE)) {
            var parameter = getBaseParameters(32, Color.GRAY);
            BitmapFont font = arialGenerator.generateFont(parameter);
            font.getData().markupEnabled = true;
            fonts.put(CARD_TEXT_CODE, font);

        }
        return fonts.get(CARD_TEXT_CODE);
    }

    public BitmapFont getBattlerCardStatFont() {
        if (!fonts.containsKey(BATTLER_CARD_STAT_CODE)) {
            var parameter = getBaseParameters(64, Color.BLACK);
            fonts.put(BATTLER_CARD_STAT_CODE, arialGenerator.generateFont(parameter));
        }
        return fonts.get(BATTLER_CARD_STAT_CODE);
    }

    public BitmapFont getCardManaCostFont() {
        if (!fonts.containsKey(CARD_MANA_COST_CODE)) {
            var parameter = getBaseParameters(64, Color.BLACK);
            fonts.put(CARD_MANA_COST_CODE, arialGenerator.generateFont(parameter));
        }
        return fonts.get(CARD_MANA_COST_CODE);
    }

    public BitmapFont getLeaderHealthFont() {
        if (!fonts.containsKey(LEADER_HEALTH_CODE)) {
            var parameter = getBaseParameters(48, Color.GRAY);
            fonts.put(LEADER_HEALTH_CODE, arialGenerator.generateFont(parameter));
        }
        return fonts.get(LEADER_HEALTH_CODE);
    }

    public BitmapFont getLeaderMessageFont() {
        if (!fonts.containsKey(LEADER_MESSAGE_CODE)) {
            var parameter = getBaseParameters(36, Color.BLACK);
            fonts.put(LEADER_MESSAGE_CODE, arialGenerator.generateFont(parameter));
        }
        return fonts.get(LEADER_MESSAGE_CODE);
    }

    public BitmapFont getButtonFont() {
        if (!fonts.containsKey(BUTTON_CODE)) {
            var parameter = getBaseParameters(36, Color.BLACK);
            fonts.put(BUTTON_CODE, arialGenerator.generateFont(parameter));
        }
        return fonts.get(BUTTON_CODE);
    }

    public BitmapFont getDamagePopupFont() {
        if (!fonts.containsKey(DAMAGE_POPUP_CODE)) {
            var parameter = getBaseParameters(64, Color.WHITE);
            parameter.borderColor = Color.BLACK;
            parameter.borderWidth = 3;
            fonts.put(DAMAGE_POPUP_CODE, arialGenerator.generateFont(parameter));
        }
        return fonts.get(DAMAGE_POPUP_CODE);
    }

    public BitmapFont getDeckCardsLeftFont() {
        if (!fonts.containsKey(DECK_CARDS_LEFT_CODE)) {
            var parameter = getBaseParameters(76, Color.WHITE);
            parameter.borderColor = Color.BLACK;
            parameter.borderWidth = 3;
            fonts.put(DECK_CARDS_LEFT_CODE, arialGenerator.generateFont(parameter));
        }
        return fonts.get(DECK_CARDS_LEFT_CODE);
    }

    public BitmapFont getDiscardPileSizeFont() {
        if (!fonts.containsKey(DISCARD_PILE_SIZE_CODE)) {
            var parameter = getBaseParameters(76, Color.WHITE);
            parameter.borderColor = Color.BLACK;
            parameter.borderWidth = 3;
            fonts.put(DISCARD_PILE_SIZE_CODE, arialGenerator.generateFont(parameter));
        }
        return fonts.get(DISCARD_PILE_SIZE_CODE);
    }

    public void dispose() {
        for (BitmapFont font : fonts.values()) {
            font.dispose();
        }
        arialGenerator.dispose();

    }
}
