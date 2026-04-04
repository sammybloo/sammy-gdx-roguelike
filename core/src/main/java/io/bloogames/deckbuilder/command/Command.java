package io.bloogames.deckbuilder.command;

import io.bloogames.deckbuilder.screen.Battle;

public interface Command {
    void execute(Battle battle);
}
