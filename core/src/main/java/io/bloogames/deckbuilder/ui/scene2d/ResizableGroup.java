package io.bloogames.deckbuilder.ui.scene2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.ui.color.Tint;
import io.bloogames.deckbuilder.ui.color.TintSet;
import io.bloogames.deckbuilder.ui.color.Tintable;

public class ResizableGroup extends Group implements Tintable {
    private final TintSet tintSet = new TintSet();
    public float targetWidth;
    public float targetHeight;
    public boolean valid = false;
    ObjectMap<Actor, ResizableContainer> map = new ObjectMap<>();
    private NinePatch background;
    private Color parentColor = null;

    public ResizableGroup(float targetWidth, float targetHeight) {
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
        tintSet.setParent(this);
    }

    public void invalidate() {
        valid = false;
    }

    public void resize() {
        float scaleX = getWidth() / targetWidth;
        float scaleY = getHeight() / targetHeight;

        for (Actor actor : map.keys()) {
            if (actor instanceof Label label) {
                label.setFontScale(Math.max(0.01f, scaleX), Math.max(0.01f, scaleY));
            }
            ResizableContainer container = map.get(actor);
            Bounds bounds = calculate(container.getSettings(), scaleX, scaleY);
            container.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
        }

        setColor(parentColor);

        valid = true;
    }

    public void register(Actor actor, ResizableSettings settings) {
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

    private Bounds calculate(ResizableSettings settings, float scaleX, float scaleY) {
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

    public void setBackground(NinePatch background) {
        this.background = background;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (!valid) {
            resize();
        }

        if (background != null) {
            float alpha = getColor().a * parentAlpha;

            Color oldBatchColor = batch.getColor();
            batch.setColor(getColor().r, getColor().g, getColor().b, alpha);

            background.draw(batch, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                getScaleX(), getScaleY(), getRotation());

            batch.setColor(oldBatchColor);
        }
        super.draw(batch, parentAlpha);
    }

    @Override
    public void addTint(Tint tint) {
        tintSet.addTint(tint);

    }

    @Override
    public void removeTint(Tint tint) {
        tintSet.removeTint(tint);
    }

    @Override
    public void removeTint(String id) {
        tintSet.removeTint(id);
    }

    @Override
    public void setColor(Color color) {
        parentColor = color;
        Color newColor;
        if (parentColor == null) {
            newColor = tintSet.getColor();
        } else {
            newColor = tintSet.getColor(color);
        }

        super.setColor(newColor);

        map.forEach(entry -> {
            if (!entry.value.getSettings().keepColour) {
                entry.key.setColor(newColor);
            }
        });
    }

    @Override
    public void setColor(float r, float g, float b, float a) {
        setColor(new Color(r, g, b, a));
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        invalidate();
    }

    @Override
    public void refreshColour() {
        setColor(parentColor);
    }

    public float getTargetWidth() {
        return targetWidth;
    }

    public float getTargetHeight() {
        return targetHeight;
    }

    private record Bounds(float x, float y, float width, float height) {
    }
}
