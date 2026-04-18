package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

public enum TextManager {
    INSTANCE;

    private final I18NBundle errorMessages;

    TextManager() {
        errorMessages = AssetManager.INSTANCE.getBundle("errors", Locale.ENGLISH);
    }

    public String getErrorMessage(String errorCode) {
        return errorMessages.get(errorCode);
    }
}
