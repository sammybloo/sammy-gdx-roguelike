package io.bloogames.deckbuilder.handler;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Null;
import io.bloogames.deckbuilder.controller.BattleController;
import io.bloogames.deckbuilder.scene2d.HoverListener;
import io.bloogames.deckbuilder.ui.ViewUtils;
import io.bloogames.deckbuilder.view.BattlerView;
import io.bloogames.deckbuilder.view.SlotView;
import io.bloogames.deckbuilder.view.TableauView;

public class TableauSwapHandler {

    private final TableauView tableau;
    private final DragAndDrop dragAndDrop;
    private final BattleController battleController;

    public TableauSwapHandler(TableauView tableau, BattleController battleController) {
        this.tableau = tableau;
        this.dragAndDrop = new DragAndDrop();
        this.dragAndDrop.setKeepWithinStage(false);
        this.dragAndDrop.setDragTime(0);
        this.battleController = battleController;

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
                        battleController.swapSlots(tableau.getModel(), slot.getModel(), otherSlot.getModel());
                    }
                }
            );

            if (slot.getBattler() != null) {
                slot.getBattler().addListener(new HoverListener(0f, 0f) {
                    BattlerView battler = slot.getBattler();
                    @Override
                    public void onHoverStart(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        if (battleController.getBattleState().canSwapBattlers()) {
                            battler.setHovered(true);
                        }
                    }

                    @Override
                    public void onHoverEnd(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        if (battleController.getBattleState().canSwapBattlers()) {
                            battler.setHovered(false);
                        }
                    }
                });
                dragAndDrop.addSource(
                    new DragAndDrop.Source(slot.getBattler()) {
                        @Override
                        public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                            if (!battleController.getBattleState().canSwapBattlers()) {
                                return null;
                            }
                            var payload = new DragAndDrop.Payload();
                            var battler = slot.getBattler();
                            ViewUtils.unmoor(battler);
                            slot.removeBattler();
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
}
