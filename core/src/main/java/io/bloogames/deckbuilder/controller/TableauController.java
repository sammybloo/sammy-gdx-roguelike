package io.bloogames.deckbuilder.controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Null;
import io.bloogames.deckbuilder.command.SwapBattlerCommand;
import io.bloogames.deckbuilder.manager.CommandManager;
import io.bloogames.deckbuilder.view.Battler;
import io.bloogames.deckbuilder.view.Slot;
import io.bloogames.deckbuilder.view.Tableau;

public class TableauController {

    private final Tableau tableau;
    private final DragAndDrop dragAndDrop;
    private boolean enabled;

    public TableauController(Tableau tableau, boolean enabled) {
        this.tableau = tableau;
        this.enabled = enabled;
        this.dragAndDrop = new DragAndDrop();
        this.dragAndDrop.setKeepWithinStage(false);
        this.dragAndDrop.setDragTime(0);

        rebuild();
    }

    public void rebuild() {
        dragAndDrop.clear();

        if (!enabled) return;

        for (Slot slot : tableau.getSlots()) {
            dragAndDrop.addTarget(
                new DragAndDrop.Target(slot) {
                    @Override
                    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        return true;
                    }

                    @Override
                    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        Battler battler = (Battler) payload.getObject();
                        Slot oldSlot = tableau.getSlot(battler);
                        CommandManager.INSTANCE.processImmediately(new SwapBattlerCommand(tableau.getModel(), oldSlot.getModel(), slot.getModel()));
                    }
                }
            );

            if (slot.getBattler() != null) {
                dragAndDrop.addSource(
                    new DragAndDrop.Source(slot.getBattler()) {
                        @Override
                        public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                            var payload = new DragAndDrop.Payload();
                            var battler = slot.getBattler();
                            tableau.getStage().addActor(battler);
                            payload.setObject(battler);
                            payload.setDragActor(battler);
                            dragAndDrop.setDragActorPosition(battler.getWidth() / 2, -(battler.getHeight() / 2));
                            return payload;
                        }

                        public void dragStop(InputEvent event, float x, float y, int pointer,
                                             @Null DragAndDrop.Payload payload, @Null DragAndDrop.Target target) {
                            if (target == null || target.getActor() == slot) {
                                slot.resetBattler();
                            }
                        }
                    }
                );
            }
        }
    }

    public DragAndDrop getDragAndDrop() {
        return dragAndDrop;
    }
}
