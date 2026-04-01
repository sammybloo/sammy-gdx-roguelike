package io.bloogames.deckbuilder.command;

import io.bloogames.deckbuilder.screen.BattleScreen;
import io.bloogames.deckbuilder.view.Card;

public class CancelTargetCommand extends Command {

    Card card;

    public CancelTargetCommand(Card card) {
        this.card = card;
    }

    @Override
    public void execute(BattleScreen battleScreen) {
        battleScreen.getPlayerHand().returnCard(card);
        battleScreen.getTargetSystem().cancelTargeting();
    }
}
