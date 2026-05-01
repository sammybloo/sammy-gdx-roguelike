package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.model.aura.Aura;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public class TableauModel {
    private final int size;
    private final Array<SlotModel> slots;
    private final Ownership ownership;

    public TableauModel(int size, Ownership.Type owner) {
        this.size = size;
        this.slots = new Array<>();
        this.ownership = new Ownership(owner);

        for (int i = 0; i < size; i++) {
            slots.add(new SlotModel(owner));
        }
    }

    public SlotModel getSlot(int index) {
        return slots.get(index);
    }

    public Array<SlotModel> getSlots() {
        return slots;
    }

    public int getSize() {
        return size;
    }

    public Ownership getOwnership() {
        return ownership;
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

    public void addAllAuras(Array<Aura> arr) {
        for (SlotModel slot : slots) {
            slot.addAllAuras(arr);
            if (slot.hasBattler()) {
                slot.getBattler().addAllAuras(arr);
            }
        }
    }
}
