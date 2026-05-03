package io.bloogames.deckbuilder.ui.scene2d;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AnimatedImage extends Image {
    private final Animation<TextureRegion> animation;
    private float stateTime;

    public AnimatedImage(Animation<TextureRegion> animation) {
        this.animation = animation;
        reset();
    }

    public void reset() {
        stateTime = 0f;
        updateFrame(0f);
    }

    private void updateFrame(float time) {
        if (animation == null) {
            return;
        }

        TextureRegion frame = animation.getKeyFrame(time);
        if (frame == null) {
            return;
        }

        setDrawable(new TextureRegionDrawable(frame));
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (animation == null) {
            return;
        }

        stateTime += delta;
        updateFrame(stateTime);
    }
}
