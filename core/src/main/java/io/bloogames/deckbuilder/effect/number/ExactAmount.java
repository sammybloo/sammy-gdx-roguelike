package io.bloogames.deckbuilder.effect.number;

import io.bloogames.deckbuilder.effect.context.TargetContext;

public class ExactAmount extends Amount {
    public ExactAmount(int baseNumber) {
        super(baseNumber);
    }

    @Override
    public void calculateNumber(TargetContext<?> context) {
        setCurrentNumber(getBaseNumber());
    }
}
