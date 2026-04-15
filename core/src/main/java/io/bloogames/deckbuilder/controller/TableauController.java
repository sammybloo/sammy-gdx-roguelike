package io.bloogames.deckbuilder.controller;

import com.badlogic.gdx.utils.Null;
import io.bloogames.deckbuilder.handler.TableauSwapHandler;
import io.bloogames.deckbuilder.model.PartyModel;
import io.bloogames.deckbuilder.view.TableauView;
import io.bloogames.deckbuilder.view.event.ViewEvent;

public class TableauController {
    @Null
    private final TableauSwapHandler tableauSwapHandler;

    public TableauController(TableauView tableau, BattleController battleController, PartyModel owner, boolean canSwap) {
        if (canSwap) {
            this.tableauSwapHandler = new TableauSwapHandler(tableau, battleController);
        }
        else {
            this.tableauSwapHandler = null;
        }

        battleController.getEventBus().register(ViewEvent.BattlerSwappedEvent.class, e -> {
            tableau.sync();
            if (tableauSwapHandler != null) {
                tableauSwapHandler.rebuild();
            }
        });
    }
}
