package io.bloogames.deckbuilder.effect.condition;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.error.ValidationError;
import io.bloogames.deckbuilder.effect.validation.ConditionValidator;

import java.util.Optional;

public interface TargetCondition<T extends Target> {
    public Optional<ValidationError> check(TargetContext<T> ctx, ConditionValidator validator);
}
