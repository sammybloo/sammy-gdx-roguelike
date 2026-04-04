package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.controller.TableauController;
import io.bloogames.deckbuilder.model.BattlerModel;
import io.bloogames.deckbuilder.model.SlotModel;
import io.bloogames.deckbuilder.model.TableauModel;

public class Tableau extends Table {

    private final Array<Slot> slots;
    private final TableauModel model;
    private final TableauController controller;

    public final static float WIDTH = 1300;
    public final static float HEIGHT = 250;
    public final static float PADDING = 5;

    public Tableau(TableauModel model) {
        this(model, true);
    }

    public Tableau(TableauModel model, boolean dragEnabled) {
        super();
        this.model = model;
        this.slots = new Array<>(model.getSize());

        setSize(WIDTH, HEIGHT);

        for (int i = 0; i < model.getSize(); i++) {
            slots.add(new Slot(model.getSlot(i)));
            add(slots.get(i)).pad(PADDING);
        }

        this.controller = new TableauController(this, dragEnabled);
        refresh();
    }

    public Array<Slot> getSlots() {
        return slots;
    }

    public TableauModel getModel() {
        return model;
    }

    public void addBattler(int index, Battler battler) {
        Slot slot = getSlot(index);
        slot.getModel().setBattler(battler.getModel());
        slot.setBattler(battler);
        refresh();
    }

    public Slot getSlot(int index) {
        return slots.get(index);
    }

    public Slot getSlot(Battler battler) {
        for (Slot s : slots) {
            if (s.hasBattler(battler)) {
                return s;
            }
        }
        return null;
    }

    private void syncFromModel() {
        ObjectMap<BattlerModel, Battler> battlerActors = new ObjectMap<>();
        for (Slot slot : slots) {
            if (slot.getBattler() != null) {
                battlerActors.put(slot.getBattler().getModel(), slot.getBattler());
            }
        }

        for (int i = 0; i < slots.size; i++) {
            Slot slotView = slots.get(i);
            SlotModel slotModel = model.getSlot(i);
            BattlerModel battlerModel = slotModel.getBattler();

            Battler battlerView = battlerModel == null ? null : battlerActors.get(battlerModel);

            if (slotView.getBattler() != battlerView) {
                slotView.setBattler(battlerView);
            }
        }
    }

    public void refresh() {
        syncFromModel();
        controller.rebuild();
    }
}
