package io.bloogames.deckbuilder.command;

import io.bloogames.deckbuilder.model.BattleModel;

public interface Command {
    void execute(BattleModel battle);
}
