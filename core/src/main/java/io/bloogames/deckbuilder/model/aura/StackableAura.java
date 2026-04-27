package io.bloogames.deckbuilder.model.aura;

public abstract class StackableAura extends Aura {
    private int stacks;

    public StackableAura(String id, int stacks) {
        super(id);
        setStacks(stacks);
    }

    public int getStacks() {
        return stacks;
    }

    public void addStacks(int amount) {
        setStacks(stacks + amount);
    }

    public void removeStacks(int amount) {
        setStacks(stacks - amount);
    }

    public void setStacks(int amount) {
        this.stacks = amount;

        if (this.stacks <= 0) {
            getOwner().removeAura(this);
        }
    }
}
