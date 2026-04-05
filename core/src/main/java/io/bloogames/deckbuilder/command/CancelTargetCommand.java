package io.bloogames.deckbuilder.command;

import com.badlogic.gdx.Gdx;
import io.bloogames.deckbuilder.model.BattleModel;
import io.bloogames.deckbuilder.screen.Battle;

public class CancelTargetCommand implements Command {

    @Override
    public void execute(BattleModel battle) {
//        if (!battle.getTargetSystem().isTargeting())
//        {
//            Gdx.app.error(CancelTargetCommand.class.getName(), "Tried to cancel targeting, but wasn't targeting.");
//        }
//        battle.getPlayerHand().returnCard(battle.getTargetSystem().getCard());
//        battle.getTargetSystem().cancelTargeting();
    }
}
