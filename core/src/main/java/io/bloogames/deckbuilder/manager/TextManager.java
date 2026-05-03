package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.utils.I18NBundle;
import io.bloogames.deckbuilder.text.ModelProperties;

import java.util.Locale;

public enum TextManager {
    INSTANCE;

    private final I18NBundle errorMessages;
    private final I18NBundle auraText;
    private final I18NBundle commonText;

    TextManager() {
        Locale locale = Locale.ENGLISH;
        errorMessages = AssetManager.INSTANCE.getBundle("errors", locale);
        auraText = AssetManager.INSTANCE.getBundle("auras", locale);
        commonText = AssetManager.INSTANCE.getBundle("common_text", locale);
    }

    public String getErrorMessage(String errorCode) {
        return errorMessages.get(errorCode);
    }

    public String getCommonTextTemplate(String key) {
        return commonText.get(key);
    }

    public String getCommonText(String key, ModelProperties modelProperties) {
        return modelProperties.parse(commonText.get(key));
    }

    public String getAuraDescription(String key, ModelProperties modelProperties) {
        return modelProperties.parse(auraText.get(key));
    }

    public String getAuraDescriptionTemplate(String key) {
        return auraText.get(key);
    }
}
