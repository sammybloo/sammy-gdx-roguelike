package io.bloogames.deckbuilder.command;

import io.bloogames.deckbuilder.screen.BattleScreen;
import io.bloogames.deckbuilder.view.Card;

public class ChooseTargetCommand extends Command {

    Card card;

    public ChooseTargetCommand(Card card) {
        this.card = card;
    }

    @Override
    public void execute(BattleScreen battleScreen) {
        battleScreen.getPlayerHand().leaveHandTemporarily(card);
        battleScreen.getTargetSystem().attemptTargeting(card);
    }
}
