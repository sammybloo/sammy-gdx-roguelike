package io.bloogames.deckbuilder.scene2d;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ObjectMap;

public class ResizableGroup extends Group {
    ObjectMap<Actor, ResizableContainer> map = new ObjectMap<>();
    public float targetWidth;
    public float targetHeight;
    public boolean valid = false;

    public ResizableGroup(float targetWidth, float targetHeight) {
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
    }

    public void invalidate() {
        valid = false;
    }

    public void resize() {
        float scaleX = getWidth() / targetWidth;
        float scaleY = getHeight() / targetHeight;

        for (Actor actor : map.keys()) {
            ResizableContainer container = map.get(actor);
            Bounds bounds = calculate(container.getSettings(), scaleX, scaleY);
            container.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
            container.setRotation(container.getSettings().rotation);
            if (actor instanceof Label) {
                ((Label) actor).setFontScale(Math.max(0.01f, scaleX), Math.max(0.01f, scaleY));
            }
        }

        valid = true;
    }

    public void register(Actor actor, ResizeableSettings settings) {
        ResizableContainer container = new ResizableContainer(actor, settings);
        map.put(actor, container);
        addActor(container);
        invalidate();
    }

    public void unregister(Actor actor) {
        ResizableContainer container = map.get(actor);
        map.remove(actor);
        container.clear();
        removeActor(container);
        invalidate();
    }

    private Bounds calculate(ResizeableSettings settings, float scaleX, float scaleY) {
        if (settings.keepAspect) {
            float scale = Math.min(scaleX, scaleY);
            scaleX = scale;
            scaleY = scale;
        }
        float width = settings.width * scaleX;
        float height = settings.height * scaleY;
        float groupWidth = getWidth();
        float groupHeight = getHeight();

        float x = switch (settings.alignment) {
            case Align.left, Align.topLeft, Align.bottomLeft -> settings.paddingX + settings.xOffset * scaleX;
            case Align.right, Align.topRight, Align.bottomRight ->
                groupWidth - width - settings.paddingX - settings.xOffset * scaleX;
            default -> (groupWidth - width) / 2f + settings.xOffset * scaleX;
        };

        float y = switch (settings.alignment) {
            case Align.bottom, Align.bottomLeft, Align.bottomRight -> settings.paddingY + settings.yOffset * scaleY;
            case Align.top, Align.topLeft, Align.topRight ->
                groupHeight - height - settings.paddingY - settings.yOffset * scaleY;
            default -> (groupHeight - height) / 2f + settings.yOffset * scaleY;
        };

        return new Bounds(x, y, width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (!valid) {
            resize();
        }
        super.draw(batch, parentAlpha);
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        invalidate();
    }

    private record Bounds(float x, float y, float width, float height) {
    }

    public static class ResizeableSettings {

        float width;
        float height;
        int alignment = Align.bottomLeft;
        float xOffset = 0f;
        float yOffset = 0f;
        float paddingX = 0f;
        float paddingY = 0f;
        float rotation = 0f;
        boolean keepAspect = false;

        public ResizeableSettings() {
        }

        public ResizeableSettings(float width, float height) {
            width(width).height(height);
        }


        public ResizeableSettings(float width, float height, int alignment) {
            width(width).height(height).alignment(alignment);
        }

        public ResizeableSettings width(float width) {
            this.width = width;
            return this;
        }

        public ResizeableSettings height(float height) {
            this.height = height;
            return this;
        }

        public ResizeableSettings alignment(int alignment) {
            this.alignment = alignment;
            return this;
        }

        public ResizeableSettings offset(float xOffset, float yOffset) {
            return xOffset(xOffset).yOffset(yOffset);
        }

        public ResizeableSettings xOffset(float xOffset) {
            this.xOffset = xOffset;
            return this;
        }

        public ResizeableSettings yOffset(float yOffset) {
            this.yOffset = yOffset;
            return this;
        }

        public ResizeableSettings paddingX(float paddingX) {
            this.paddingX = paddingX;
            return this;
        }

        public ResizeableSettings paddingY(float paddingY) {
            this.paddingY = paddingY;
            return this;
        }

        public ResizeableSettings padding(float paddingX, float paddingY) {
            return paddingX(paddingX).paddingY(paddingY);
        }

        public ResizeableSettings rotation(float rotation) {
            this.rotation = rotation;
            return this;
        }

        public ResizeableSettings keepAspect() {
            keepAspect = true;
            return this;
        }

    }
}
