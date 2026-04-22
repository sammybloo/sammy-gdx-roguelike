package io.bloogames.deckbuilder.scene2d;

import com.badlogic.gdx.utils.Align;

public class ResizableSettings {

    float width;
    float height;
    int alignment = Align.bottomLeft;
    float xOffset = 0f;
    float yOffset = 0f;
    float paddingX = 0f;
    float paddingY = 0f;
    float rotation = 0f;
    boolean keepAspect = false;
    boolean keepColour = false;

    public ResizableSettings(float width, float height) {
        width(width).height(height);
    }


    public ResizableSettings(float width, float height, int alignment) {
        width(width).height(height).alignment(alignment);
    }

    public ResizableSettings width(float width) {
        this.width = width;
        return this;
    }

    public ResizableSettings height(float height) {
        this.height = height;
        return this;
    }

    public ResizableSettings alignment(int alignment) {
        this.alignment = alignment;
        return this;
    }

    public ResizableSettings offset(float xOffset, float yOffset) {
        return xOffset(xOffset).yOffset(yOffset);
    }

    public ResizableSettings xOffset(float xOffset) {
        this.xOffset = xOffset;
        return this;
    }

    public ResizableSettings yOffset(float yOffset) {
        this.yOffset = yOffset;
        return this;
    }

    public ResizableSettings paddingX(float paddingX) {
        this.paddingX = paddingX;
        return this;
    }

    public ResizableSettings paddingY(float paddingY) {
        this.paddingY = paddingY;
        return this;
    }

    public ResizableSettings padding(float paddingX, float paddingY) {
        return paddingX(paddingX).paddingY(paddingY);
    }

    public ResizableSettings rotation(float rotation) {
        this.rotation = rotation;
        return this;
    }

    public ResizableSettings keepAspect() {
        keepAspect = true;
        return this;
    }

    public ResizableSettings keepColour() {
        keepColour = true;
        return this;
    }

}
