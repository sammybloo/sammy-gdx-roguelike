package io.bloogames.deckbuilder.card;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;

public class Tableau extends Table {
    int numSlots;
    private Array<Slot> slots;
    private Participant participant;
    private Stage stage;
    private boolean dragEnabled;

    private DragAndDrop dragAndDrop;

    public final static float WIDTH = 1750;
    public final static float HEIGHT = 350;
    public final static float PADDING = 5;

    public Tableau(int numSlots, Participant participant, Stage stage) {
        this(numSlots, participant, stage, true);

    }

    public Tableau(int numSlots, Participant participant, Stage stage, boolean dragEnabled) {
        super();
        this.participant = participant;
        this.stage = stage;
        this.numSlots = numSlots;
        this.dragEnabled = dragEnabled;
        slots = new Array<>(numSlots);
        setSize(WIDTH, HEIGHT);
        for (int i = 0; i < numSlots; i++) {
            slots.add(new Slot(participant));
            add(slots.get(i)).pad(PADDING);
        }
        generateDragAndDrop();
    }

    public void addBattler(int index, Battler battler) {
        slots.get(index).setBattler(battler);
        generateDragAndDrop();
    }

    public void generateDragAndDrop() {
        if (!dragEnabled) return;

        if (dragAndDrop == null) {
            dragAndDrop = new DragAndDrop();
            dragAndDrop.setKeepWithinStage(false);
            dragAndDrop.setDragTime(0);
        }
        dragAndDrop.clear();
        for (Slot slot : slots) {
            dragAndDrop.addTarget(
                new DragAndDrop.Target(slot) {
                    @Override
                    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        return true;
                    }

                    @Override
                    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        Battler battler = (Battler) payload.getObject();
                        Slot oldSlot = getSlot(battler);

                        if (slot.hasBattler()) {
                            Battler currentBattler = slot.getBattler();
                            slot.setBattler(battler);
                            oldSlot.setBattler(currentBattler);
                        }
                        else {
                            oldSlot.removeBattler();
                            slot.setBattler(battler);
                        }

                        generateDragAndDrop();
                    }
                }
            );

            if (slot.getBattler() != null) {
                dragAndDrop.addSource(new DragAndDrop.Source(slot.getBattler()) {
                    @Override
                    public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                        var payload = new DragAndDrop.Payload();
                        var battler = slot.getBattler();
                        stage.addActor(battler);
                        payload.setObject(battler);
                        payload.setDragActor(battler);
                        dragAndDrop.setDragActorPosition(battler.getWidth() / 2, - (battler.getHeight() / 2));
                        return payload;
                    }

                    public void dragStop (InputEvent event, float x, float y, int pointer,
                                          @Null DragAndDrop.Payload payload, @Null DragAndDrop.Target target) {
                        if (target == null) {
                            Battler battler = (Battler) (payload.getObject());
                            slot.setBattler(battler);
                        }
                                          }
                }
                );
            }
        }
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
}
