package io.bloogames.deckbuilder.vfx;

import com.badlogic.gdx.utils.DelayedRemovalArray;

public enum VFXManager {
    INSTANCE;

    private DelayedRemovalArray<VisualEffect> visualEffects = new DelayedRemovalArray<>();

    public void addEffect(VisualEffect visualEffect) {
        visualEffects.add(visualEffect);
        visualEffect.play();
    }

    public boolean isReady() {
        visualEffects.begin();

        boolean isReady = true;
        for (int i = 0; i < visualEffects.size; i++) {
            VisualEffect vfx = visualEffects.get(i);
            if (vfx.isReady()) {
                visualEffects.removeIndex(i);
            }
            else {
                isReady = false;
            }
        }
        visualEffects.end();

        return isReady;
    }
}
