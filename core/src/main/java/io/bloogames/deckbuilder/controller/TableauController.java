package io.bloogames.deckbuilder.controller;

import com.badlogic.gdx.utils.Null;
import io.bloogames.deckbuilder.handler.TableauSwapHandler;
import io.bloogames.deckbuilder.view.TableauView;
import io.bloogames.deckbuilder.view.event.ViewEvent;

import java.util.Optional;

public class TableauController {
    @Null
    private final TableauSwapHandler tableauSwapHandler;

    public TableauController(TableauView tableau, BattleController battleController, boolean canSwap) {
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
