package io.bloogames.deckbuilder.effect.number;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.text.Describable;

public abstract class Amount implements Describable {
    float baseNumber;
    float currentNumber;
    float calculateNumber;
    boolean intDefault = true;

    public Amount(int baseNumber) {
        this.baseNumber = baseNumber;
        this.currentNumber = baseNumber;
    }

    public float getBaseNumber() {
        return baseNumber;
    }

    public int getBaseInt() {
        return Math.round(baseNumber);
    }

    public float getCurrentNumber() {
        return currentNumber;
    }

    protected void setCurrentNumber(float currentNumber) {
        this.currentNumber = currentNumber;
    }

    public int getCurrentInt() {
        return Math.round(currentNumber);
    }

    // Should set current number
    public abstract void calculateNumber(TargetContext<?> context);

    @Override
    public String description() {
        if (intDefault) {
            return getCurrentInt() + "";
        }
        return getCurrentNumber() + "";
    }
}
