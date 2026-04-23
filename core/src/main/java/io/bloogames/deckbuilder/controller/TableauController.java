package io.bloogames.deckbuilder.controller;

import com.badlogic.gdx.utils.Null;
import io.bloogames.deckbuilder.handler.TableauSwapHandler;
import io.bloogames.deckbuilder.model.BattlerModel;
import io.bloogames.deckbuilder.model.PartyModel;
import io.bloogames.deckbuilder.vfx.DamageVisualEffect;
import io.bloogames.deckbuilder.vfx.VFXManager;
import io.bloogames.deckbuilder.view.BattlerView;
import io.bloogames.deckbuilder.view.SlotView;
import io.bloogames.deckbuilder.view.TableauView;
import io.bloogames.deckbuilder.view.event.ViewEvent;

public class TableauController {
    @Null
    private TableauSwapHandler tableauSwapHandler;
    private final TableauView tableau;
    private final BattleController battleController;

    public TableauController(TableauView tableau, BattleController battleController, PartyModel owner) {
        this.battleController = battleController;
        this.tableau = tableau;

        battleController.getEventBus().register(ViewEvent.BattlerSwappedEvent.class, e -> {
            sync();
        });

        battleController.getEventBus().register(ViewEvent.BattleViewStateEvent.class, e -> {
            sync();
        });

        battleController.getEventBus().register(ViewEvent.DamageDealtEvent.class, e -> {
            if (e.target() instanceof BattlerModel battlerModel) {
                BattlerView battlerView = tableau.getBattler(battlerModel);
                if (battlerView != null) {
                    VFXManager.INSTANCE.addEffect(new DamageVisualEffect(battlerView, battlerView, e.amount()));
                }
            }
        });

        battleController.getEventBus().register(ViewEvent.BattlerAddedEvent.class, e -> {
            SlotView slot = tableau.getSlot(e.slot());
            if (slot != null) {
                slot.setBattler(e.battler());
                slot.getBattler().playEntry();
            }
        });
    }

    public void enableSwapping() {
        this.tableauSwapHandler = new TableauSwapHandler(tableau, battleController);
    }

    private void sync() {
        tableau.sync();
        if (tableauSwapHandler != null) {
            tableauSwapHandler.rebuild();
        }
    }
}
