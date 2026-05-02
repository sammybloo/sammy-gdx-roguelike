package io.bloogames.deckbuilder.model.ownership;

public class Ownership {

    private final Type originalOwner;
    private Type currentOwner;

    public Ownership(Type originalOwner) {
        this.originalOwner = originalOwner;
        this.currentOwner = originalOwner;
    }

    public Type getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(Type currentOwner) {
        this.currentOwner = currentOwner;
    }

    public Type getOriginalOwner() {
        return originalOwner;
    }

    public enum Type {
        NONE,
        PLAYER,
        ENEMY;

        public boolean isOwn(Type type) {
            if (this == NONE) {
                return false;
            } else return this == type;
        }

        public boolean isEnemy(Type type) {
            if (this == NONE || type == NONE) {
                return false;
            } else return this != type;
        }
    }
}
