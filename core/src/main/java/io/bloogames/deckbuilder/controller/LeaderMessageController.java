package io.bloogames.deckbuilder.controller;

import io.bloogames.deckbuilder.manager.TextManager;
import io.bloogames.deckbuilder.view.LeaderMessageView;
import io.bloogames.deckbuilder.view.event.ViewEvent;

public class LeaderMessageController
{
    public LeaderMessageController(LeaderMessageView leaderMessageView, BattleController battleController) {
        battleController.getEventBus().register(ViewEvent.CardStartFailedEvent.class,
            e -> leaderMessageView.showMessage(TextManager.INSTANCE.getErrorMessage(e.error().errorId())));
        battleController.getEventBus().register(ViewEvent.CardFailedEvent.class,
            e -> leaderMessageView.showMessage(TextManager.INSTANCE.getErrorMessage(e.error().errorId())));
    }
}
