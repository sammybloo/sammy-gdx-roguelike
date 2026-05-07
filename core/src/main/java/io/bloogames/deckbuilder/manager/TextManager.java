package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.utils.I18NBundle;
import io.bloogames.deckbuilder.text.DescriptionProperties;

import java.util.Locale;
import java.util.Optional;

public enum TextManager {
    INSTANCE;

    private final I18NBundle errorMessages;
    private final I18NBundle auraText;
    private final I18NBundle cardText;
    private final I18NBundle commonText;

    TextManager() {
        Locale locale = Locale.ENGLISH;
        errorMessages = AssetManager.INSTANCE.getBundle("errors", locale);
        auraText = AssetManager.INSTANCE.getBundle("auras", locale);
        cardText = AssetManager.INSTANCE.getBundle("cards", locale);
        commonText = AssetManager.INSTANCE.getBundle("common_text", locale);
    }

    public String getErrorMessage(String errorCode) {
        return errorMessages.get(errorCode);
    }

    public String getCommonTextTemplate(String key) {
        return commonText.get(key);
    }

    public String getCommonText(String key, DescriptionProperties descriptionProperties) {
        return descriptionProperties.parse(commonText.get(key));
    }

    public String getAuraDescription(String key, DescriptionProperties descriptionProperties) {
        return descriptionProperties.parse(auraText.get(key));
    }

    public String getCardName(String key) {
        return cardText.get(key + "_name");
    }

    public Optional<String> getCardDescription(String key, DescriptionProperties descriptionProperties) {
        if (!cardText.keys().contains(key + "_desc")) {
            return Optional.empty();
        }
        return Optional.ofNullable(descriptionProperties.parse(cardText.get(key + "_desc")));
    }

}
