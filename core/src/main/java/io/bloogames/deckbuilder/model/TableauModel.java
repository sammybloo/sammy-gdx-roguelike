package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;

public class TableauModel {
    private int size;
    private Array<SlotModel> slots;
    private LeaderModel participant;

    public TableauModel(int size, LeaderModel participant) {
        this.size = size;
        this.slots = new Array<>();
        this.participant = participant;

        for (int i = 0; i < size; i++) {
            slots.add(new SlotModel(participant));
        }
    }

    public SlotModel getSlot(int index) {
        return slots.get(index);
    }

    public int getSize() {
        return size;
    }
}
