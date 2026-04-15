package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.event.GameEvent;

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

    public void swapBattlers(BattleModel battle, int slotIndex1, int slotIndex2) {
        swapBattlers(battle, getSlot(slotIndex1), getSlot(slotIndex2));
    }

    public void swapBattlers(BattleModel battle, SlotModel slot1, SlotModel slot2) {
        BattlerModel battler1 = slot1.getBattler();
        slot1.setBattler(slot2.getBattler());
        slot2.setBattler(battler1);
        battle.dispatch(new GameEvent.BattlersSwappedEvent(this, slot1, slot2));
    }
}
