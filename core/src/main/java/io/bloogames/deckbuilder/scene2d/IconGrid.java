package io.bloogames.deckbuilder.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class IconGrid extends WidgetGroup {
    private PrimaryDirection primary = PrimaryDirection.HORIZONTAL;
    private HorizontalAlign hAlign = HorizontalAlign.LEFT_TO_RIGHT;
    private VerticalAlign vAlign = VerticalAlign.TOP_TO_BOTTOM;

    private int fixedCount = 1; // rows if horizontal, cols if vertical
    private float spacing = 5f;

    public IconGrid(PrimaryDirection primary, HorizontalAlign hAlign, VerticalAlign vAlign, int fixedCount, float spacing) {
        this.primary = primary;
        this.hAlign = hAlign;
        this.vAlign = vAlign;
        this.fixedCount = fixedCount;
        this.spacing = spacing;
    }

    @Override
    public void layout() {
        float width = getWidth();
        float height = getHeight();
        int count = getChildren().size;

        if (count == 0) return;

        float cellSize;
        int rows, cols;

        if (primary == PrimaryDirection.HORIZONTAL) {
            rows = fixedCount;
            cellSize = (height - (rows - 1) * spacing) / rows;
            cols = Math.max(1, (int)(width / (cellSize + spacing)));
        } else {
            cols = fixedCount;
            cellSize = (width - (cols - 1) * spacing) / cols;
            rows = Math.max(1, (int)(height / (cellSize + spacing)));
        }

        for (int i = 0; i < count; i++) {
            int row, col;

            if (primary == PrimaryDirection.HORIZONTAL) {
                row = i / cols;
                col = i % cols;
            } else {
                col = i / rows;
                row = i % rows;
            }

            // Horizontal direction (this one is fine to flip index)
            if (hAlign == HorizontalAlign.RIGHT_TO_LEFT) {
                col = cols - 1 - col;
            }

            float x = col * (cellSize + spacing);

            float y;
            if (vAlign == VerticalAlign.TOP_TO_BOTTOM) {
                // origin at top
                y = height - (row + 1) * cellSize - row * spacing;
            } else {
                // origin at bottom
                y = row * (cellSize + spacing);
            }

            Actor child = getChildren().get(i);
            child.setBounds(x, y, cellSize, cellSize);
        }
    }

    public enum PrimaryDirection {
        HORIZONTAL, // fill row first
        VERTICAL    // fill column first
    }

    public enum HorizontalAlign {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    public enum VerticalAlign {
        TOP_TO_BOTTOM,
        BOTTOM_TO_TOP
    }


}
