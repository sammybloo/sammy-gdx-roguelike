package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.controller.TableauController;
import io.bloogames.deckbuilder.model.BattlerModel;
import io.bloogames.deckbuilder.model.SlotModel;
import io.bloogames.deckbuilder.model.TableauModel;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.View;

public class TableauView extends ResizableGroup implements View {
    ObjectMap<BattlerModel, BattlerView> battlerActors = new ObjectMap<>();
    private final Array<SlotView> slots;
    private final TableauModel model;
    private final TableauController controller;

    public final static float WIDTH = 1290;
    public final static float HEIGHT = 250;
    public final static float PADDING = 5;

    public TableauView(TableauModel model) {
        this(model, true);
    }

    public TableauView(TableauModel model, boolean dragEnabled) {
        super(WIDTH, HEIGHT);
        this.model = model;
        this.slots = new Array<>(model.getSize());

        for (int i = 0; i < model.getSize(); i++) {
            slots.add(new SlotView(model.getSlot(i)));
            register(slots.get(i), new ResizeableSettings(250, 250).xOffset(i * (260)));
        }

        this.controller = new TableauController(this, dragEnabled);
        update();
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
        update();
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

    @Override
    public void update() {
        for (SlotView slotView : slots) {
            slotView.update();
        }
        controller.rebuild();
    }
}
