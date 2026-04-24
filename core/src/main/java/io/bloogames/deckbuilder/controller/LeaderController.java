package io.bloogames.deckbuilder.controller;

import io.bloogames.deckbuilder.vfx.VFXManager;
import io.bloogames.deckbuilder.vfx.concrete.DamageVisualEffect;
import io.bloogames.deckbuilder.view.LeaderView;
import io.bloogames.deckbuilder.view.ManaView;
import io.bloogames.deckbuilder.view.event.ViewEvent;

public class LeaderController {

    public LeaderController(LeaderView leaderView, ManaView manaView, BattleController battleController) {
        battleController.getEventBus().register(ViewEvent.DamageDealtEvent.class, (event -> {
            if (event.target() == leaderView.getModel()) {
                VFXManager.INSTANCE.addEffect(new DamageVisualEffect(leaderView, leaderView, event.amount()));
            }
        }));

        battleController.getEventBus().register(ViewEvent.ManaSpentEvent.class, e -> manaView.sync());
    }
}
