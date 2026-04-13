package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.event.GameEventDispatcher;

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

    public void swapBattlers(int slotIndex1, int slotIndex2, GameEventDispatcher eventDispatcher) {
        SlotModel slot1 = getSlot(slotIndex1);
        SlotModel slot2 = getSlot(slotIndex2);
        BattlerModel battler1 = slot1.getBattler();
        slot1.setBattler(slot2.getBattler());
        slot2.setBattler(slot1.getBattler());

    }
}
