package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.model.BattlerModel;
import io.bloogames.deckbuilder.model.SlotModel;
import io.bloogames.deckbuilder.model.TableauModel;
import io.bloogames.deckbuilder.ui.View;
import io.bloogames.deckbuilder.ui.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.scene2d.ResizableSettings;

public class TableauView extends ResizableGroup implements View {
    public final static float WIDTH = 1290;
    public final static float HEIGHT = 250;
    private final Array<SlotView> slots;
    private final TableauModel model;
    ObjectMap<BattlerModel, BattlerView> battlerActors = new ObjectMap<>();

    public TableauView(TableauModel model) {
        super(WIDTH, HEIGHT);
        this.model = model;
        this.slots = new Array<>(model.getSize());
        setTouchable(Touchable.childrenOnly);

        for (int i = 0; i < model.getSize(); i++) {
            slots.add(new SlotView(model.getSlot(i)));
            register(slots.get(i), new ResizableSettings(250, 250).xOffset(i * (260)));
        }

        sync();
    }

    public Array<SlotView> getSlots() {
        return slots;
    }

    public TableauModel getModel() {
        return model;
    }

    public void addBattler(int index, BattlerModel battlerModel) {
        SlotView slot = getSlot(index);
        slot.setBattler(battlerModel);
        battlerActors.put(battlerModel, slot.getBattler());
        sync();
    }

    public SlotView getSlot(int index) {
        return slots.get(index);
    }

    public SlotView getSlot(BattlerModel battlerModel) {
        for (SlotView s : slots) {
            if (s.hasBattler(battlerModel)) {
                return s;
            }
        }
        return null;
    }

    public SlotView getSlot(SlotModel slotModel) {
        for (SlotView s : slots) {
            if (s.getModel() == slotModel) {
                return s;
            }
        }
        return null;
    }

    public BattlerView getBattler(BattlerModel battlerModel) {
        for (SlotView s : slots) {
            if (s.hasBattler(battlerModel)) {
                return s.getBattler();
            }
        }
        return null;
    }

    @Override
    public void sync() {
        for (SlotView slotView : slots) {
            slotView.sync();
        }
    }
}
