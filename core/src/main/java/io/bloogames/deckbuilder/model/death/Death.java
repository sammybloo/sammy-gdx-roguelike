package io.bloogames.deckbuilder.model.death;

import com.badlogic.gdx.utils.Array;

import java.util.Comparator;
import java.util.Optional;

public class Death {
    private final Array<DeathPreventer> preventers = new Array<>();
    private Type type;
    private NextLocation nextLocation = NextLocation.DISCARD_PILE;

    public Death(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public NextLocation getNextLocation() {
        return nextLocation;
    }

    public void setNextLocation(NextLocation nextLocation) {
        this.nextLocation = nextLocation;
    }

    public void sortPreventers() {
        preventers.sort(Comparator.comparingInt((DeathPreventer preventer) -> preventer.priority().getSpeed()).reversed());
    }

    public Optional<DeathPreventer> getPreventer() {
        if (preventers.size == 0) {
            return Optional.empty();
        }

        sortPreventers();

        return Optional.of(preventers.get(0));
    }

    public enum Type {
        NO_MAX_HEALTH,
        DAMAGE,
        ACTION,
        EFFECT
    }

    public enum NextLocation {
        NOWHERE,
        DISCARD_PILE
    }
}
