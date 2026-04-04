package io.bloogames.deckbuilder.command;

import io.bloogames.deckbuilder.screen.BattleScreen;

public interface Command {
    void execute(BattleScreen battle);
}
