package io.bloogames.deckbuilder.command;

import io.bloogames.deckbuilder.manager.CommandManager;
import io.bloogames.deckbuilder.model.BattleModel;
import io.bloogames.deckbuilder.screen.Battle;
import io.bloogames.deckbuilder.view.CardView;

public class ChooseTargetCommand implements Command {

    CardView card;

    public ChooseTargetCommand(CardView card) {
        this.card = card;
    }

    @Override
    public void execute(BattleModel battle) {
//        if (battle.getTargetSystem().isTargeting()) {
//            CommandManager.INSTANCE.processImmediately(
//                new CancelTargetCommand());
//        }
//        battle.getPlayerHand().leaveTemporarily(card);
//        battle.getTargetSystem().attemptTargeting(card);
    }
}
