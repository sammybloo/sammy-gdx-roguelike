package io.bloogames.deckbuilder.card;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class Tableau extends Table {
    int numSlots;
    private Array<Slot> slots;

    public final static float WIDTH = 1920;
    public final static float HEIGHT = 350;
    public final static float PADDING = 5;

    public Tableau(int numSlots) {
        super();
        this.numSlots = numSlots;
        slots = new Array<>(numSlots);
        setSize(WIDTH, HEIGHT);
        //align(Align.center);
        for (int i = 0; i < numSlots; i++) {
            slots.add(new Slot());
            add(slots.get(i)).pad(PADDING);
        }
    }

    public Slot getSlot(int index) {
        return slots.get(index);
    }
}
