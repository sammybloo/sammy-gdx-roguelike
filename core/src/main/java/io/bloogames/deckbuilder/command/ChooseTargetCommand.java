package io.bloogames.deckbuilder.command;

import io.bloogames.deckbuilder.manager.CommandManager;
import io.bloogames.deckbuilder.screen.Battle;
import io.bloogames.deckbuilder.view.Card;

public class ChooseTargetCommand implements Command {

    Card card;

    public ChooseTargetCommand(Card card) {
        this.card = card;
    }

    @Override
    public void execute(Battle battle) {
        if (battle.getTargetSystem().isTargeting()) {
            CommandManager.INSTANCE.processImmediately(
                new CancelTargetCommand());
        }
        battle.getPlayerHand().leaveTemporarily(card);
        battle.getTargetSystem().attemptTargeting(card);
    }
}
