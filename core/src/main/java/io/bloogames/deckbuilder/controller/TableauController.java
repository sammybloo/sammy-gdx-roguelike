package io.bloogames.deckbuilder.controller;

import com.badlogic.gdx.utils.Null;
import io.bloogames.deckbuilder.effect.target.concrete.BattlerTarget;
import io.bloogames.deckbuilder.effect.target.concrete.SlotTarget;
import io.bloogames.deckbuilder.handler.TableauSwapHandler;
import io.bloogames.deckbuilder.model.PartyModel;
import io.bloogames.deckbuilder.ui.HighlightState;
import io.bloogames.deckbuilder.view.BattlerView;
import io.bloogames.deckbuilder.view.SlotView;
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

        battleController.getEventBus().register(ViewEvent.CardStartEvent.class, e -> {
            for (SlotView slot : tableau.getSlots()) {
                if (battleController.isValidTarget(e.cardSource(), new SlotTarget(slot.getModel(), owner))) {
                    slot.setHighlightState(HighlightState.VALID);
                }
                else {
                    slot.setHighlightState(HighlightState.INVALID);
                }

                if (slot.hasBattler()) {
                    BattlerView battler = slot.getBattler();
                    if (battleController.isValidTarget(e.cardSource(), new BattlerTarget(battler.getModel(), owner))) {
                        battler.setHighlightState(HighlightState.VALID);
                    }
                    else {
                        battler.setHighlightState(HighlightState.INVALID);
                    }
                }
            }
        });
    }
}
