package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.command.Command;
import io.bloogames.deckbuilder.screen.BattleScreen;

public enum CommandManager {
    INSTANCE;

    private Array<Command> deque = new Array<>();
    private BattleScreen battleScreen;

    public void setBattle(BattleScreen battle) {
        this.battleScreen = battle;
    }
    public void processImmediately(Command command) {
        command.execute(battleScreen);
    }

    public void queue(Command command) {
        deque.add(command);
    }

    public void stack(Command command) {
        deque.insert(0, command);
    }

    public void processQueue() {
        processImmediately(deque.pop());
    }
}
