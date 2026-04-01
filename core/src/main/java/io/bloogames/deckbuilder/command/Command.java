package io.bloogames.deckbuilder.command;

import io.bloogames.deckbuilder.screen.BattleScreen;

public abstract class Command {
    public abstract void execute(BattleScreen battle);
}
