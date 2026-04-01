package io.bloogames.deckbuilder.command;

import com.badlogic.gdx.Gdx;
import io.bloogames.deckbuilder.screen.BattleScreen;
import io.bloogames.deckbuilder.view.Card;

public class CancelTargetCommand extends Command {

    @Override
    public void execute(BattleScreen battleScreen) {
        if (!battleScreen.getTargetSystem().isTargeting())
        {
            Gdx.app.error(CancelTargetCommand.class.getName(), "Tried to cancel targeting, but wasn't targeting.");
        }
        battleScreen.getPlayerHand().returnCard(battleScreen.getTargetSystem().getCard());
        battleScreen.getTargetSystem().cancelTargeting();
    }
}
