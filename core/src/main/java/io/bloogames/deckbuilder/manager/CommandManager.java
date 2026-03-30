package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.action.Command;

public enum CommandManager {
    INSTANCE;

    private Array<Command> deque = new Array<>();

    public void processImmediately(Command command) {
        command.execute();
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
