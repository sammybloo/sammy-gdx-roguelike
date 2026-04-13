package io.bloogames.deckbuilder.handler;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Null;
import io.bloogames.deckbuilder.view.BattlerView;
import io.bloogames.deckbuilder.view.SlotView;
import io.bloogames.deckbuilder.view.TableauView;

public class TableauSwapHandler implements Handler {

    private final TableauView tableau;
    private final DragAndDrop dragAndDrop;

    public TableauSwapHandler(TableauView tableau) {
        this.tableau = tableau;
        this.dragAndDrop = new DragAndDrop();
        this.dragAndDrop.setKeepWithinStage(false);
        this.dragAndDrop.setDragTime(0);

        rebuild();
    }

    public void rebuild() {
        dragAndDrop.clear();

        for (SlotView slot : tableau.getSlots()) {
            dragAndDrop.addTarget(
                new DragAndDrop.Target(slot) {
                    @Override
                    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        return true;
                    }

                    @Override
                    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        payload.getDragActor().remove();
                        SlotView otherSlot = (SlotView) payload.getObject();
                        // TODO replace functionality
//                        CommandManager.INSTANCE.processImmediately(
//                            new SwapBattlerCommand(otherSlot.getModel(), slot.getModel()));

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
                            Vector2 stageCoords = battler.localToStageCoordinates(new Vector2());
                            tableau.getStage().addActor(battler);
                            slot.removeBattler();
                            battler.setPosition(stageCoords.x, stageCoords.y);
                            payload.setObject(slot);
                            payload.setDragActor(battler);
                            dragAndDrop.setDragActorPosition(battler.getWidth() / 2, -(battler.getHeight() / 2));
                            return payload;
                        }

                        public void dragStop(InputEvent event, float x, float y, int pointer,
                                             @Null DragAndDrop.Payload payload, @Null DragAndDrop.Target target) {
                            if (target == null || target.getActor() == slot) {
                                slot.setBattler((BattlerView) payload.getDragActor());
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

    @Override
    public void enable() {
        rebuild();
    }

    @Override
    public void disable() {
        dragAndDrop.clear();
    }
}
