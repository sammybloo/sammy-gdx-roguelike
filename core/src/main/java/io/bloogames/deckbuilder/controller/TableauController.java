package io.bloogames.deckbuilder.controller;

import com.badlogic.gdx.utils.Null;
import io.bloogames.deckbuilder.handler.TableauSwapHandler;
import io.bloogames.deckbuilder.model.PartyModel;
import io.bloogames.deckbuilder.model.TableauModel;
import io.bloogames.deckbuilder.view.SlotView;
import io.bloogames.deckbuilder.view.TableauView;
import io.bloogames.deckbuilder.view.event.ViewEvent;

public class TableauController {
    @Null
    private final TableauSwapHandler tableauSwapHandler;
    private final TableauView tableau;

    public TableauController(TableauView tableau, BattleController battleController, PartyModel owner, boolean canSwap) {
        if (canSwap) {
            this.tableauSwapHandler = new TableauSwapHandler(tableau, battleController);
        }
        else {
            this.tableauSwapHandler = null;
        }
        this.tableau = tableau;

        battleController.getEventBus().register(ViewEvent.BattlerSwappedEvent.class, e -> {
            sync();
        });

        battleController.getEventBus().register(ViewEvent.BattleViewStateEvent.class, e -> {
            sync();
        });

        battleController.getEventBus().register(ViewEvent.BattlerAddedEvent.class, e -> {
            SlotView slot = tableau.getSlot(e.slot());
            if (slot != null) {
                slot.setBattler(e.battler());
                slot.getBattler().playEntry();
            }
        });
    }

    private void sync() {
        tableau.sync();
        if (tableauSwapHandler != null) {
            tableauSwapHandler.rebuild();
        }
    }
}
