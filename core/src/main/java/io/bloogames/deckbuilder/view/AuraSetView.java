package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.model.aura.AuraModel;
import io.bloogames.deckbuilder.ui.scene2d.IconGrid;
import io.bloogames.deckbuilder.ui.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.scene2d.ResizableSettings;

public class AuraSetView extends ResizableGroup {

    public static final float WIDTH = 200;
    public static final float HEIGHT = 50;

    private final IconGrid iconGrid;

    public AuraSetView(IconGrid.VerticalAlign verticalAlignment) {
        super(WIDTH, HEIGHT);
        iconGrid = new IconGrid(IconGrid.PrimaryDirection.HORIZONTAL, IconGrid.HorizontalAlign.LEFT_TO_RIGHT,
            verticalAlignment, 2, 1f);
        register(iconGrid, new ResizableSettings(WIDTH, HEIGHT));
    }

    public void addAuras(Array<AuraModel> auras) {
        iconGrid.clear();
        for (AuraModel aura : auras) {
            iconGrid.addActor(new AuraView(aura));
        }
    }
}
