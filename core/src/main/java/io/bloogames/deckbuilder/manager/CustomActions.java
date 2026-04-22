package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import io.bloogames.deckbuilder.ui.color.Tint;
import io.bloogames.deckbuilder.ui.color.TintAction;

public class CustomActions {
    static {
        Actions.registerAction(TintAction::new);
    }

    public static TintAction tint(Tint tint, float endFloatBits, float duration) {
        TintAction result = Actions.action(TintAction.class);
        result.setTint(tint);
        result.setEndColor(endFloatBits);
        result.setDuration(duration);
        return result;
    }

    public static TintAction tint(Tint tint, float endFloatBits) {
        return tint(tint, endFloatBits, 0f);
    }
}
