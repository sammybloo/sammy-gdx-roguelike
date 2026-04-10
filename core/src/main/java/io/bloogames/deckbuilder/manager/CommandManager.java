package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.command.Command;
import io.bloogames.deckbuilder.model.BattleModel;

public enum CommandManager {
    INSTANCE;

    private Array<Command> deque = new Array<>();
    private BattleModel battle;

    public void setBattle(BattleModel battle) {
        this.battle = battle;
    }

    public void processImmediately(Command command) {
        command.execute(battle);
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
