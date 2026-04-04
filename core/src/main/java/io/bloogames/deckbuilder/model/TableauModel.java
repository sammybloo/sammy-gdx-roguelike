package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;

public class TableauModel {
    private int size;
    private Array<SlotModel> slots;

    public TableauModel(int size) {
        this.size = size;
        this.slots = new Array<>();

        for (int i = 0; i < size; i++) {
            slots.add(new SlotModel());
        }
    }

    public SlotModel getSlot(int index) {
        return slots.get(index);
    }

    public int getSize() {
        return size;
    }
}
