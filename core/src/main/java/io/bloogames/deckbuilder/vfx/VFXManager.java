package io.bloogames.deckbuilder.vfx;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.DelayedRemovalArray;

public enum VFXManager {
    INSTANCE;

    private final DelayedRemovalArray<VisualEffect> visualEffects = new DelayedRemovalArray<>();

    private Group vfxActorGroup;
    private Group vfxTextGroup;

    public void initialiseForStage(Stage stage) {
        vfxActorGroup = new Group();
        vfxActorGroup.setTouchable(Touchable.disabled);
        vfxTextGroup = new Group();
        vfxTextGroup.setTouchable(Touchable.disabled);

        resize(stage.getWidth(), stage.getHeight());
        stage.addActor(vfxActorGroup);
        stage.addActor(vfxTextGroup);
    }

    public void resize(float width, float height) {
        vfxActorGroup.setBounds(0, 0, width, height);
    }

    public void addToActorGroup(Actor actor) {
        vfxActorGroup.addActor(actor);
    }

    public void addToTextGroup(Actor actor) {
        vfxTextGroup.addActor(actor);
    }

    public Group getVfxActorGroup() {
        return vfxActorGroup;
    }

    public Group getVfxTextGroup() {
        return vfxTextGroup;
    }

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
            } else {
                isReady = false;
            }
        }
        visualEffects.end();

        return isReady;
    }
}
