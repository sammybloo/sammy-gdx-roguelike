package io.bloogames.deckbuilder.effect.condition.concrete.target;

import io.bloogames.deckbuilder.effect.condition.TargetCondition;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.target.concrete.SlotTarget;
import io.bloogames.deckbuilder.effect.validation.ConditionValidator;
import io.bloogames.deckbuilder.error.ValidationError;

import java.util.Optional;

public class SlotIsEmpty implements TargetCondition<SlotTarget> {

    @Override
    public Optional<ValidationError> check(TargetContext<SlotTarget> ctx, ConditionValidator validator) {
        if (ctx.target().slot().hasBattler()) {
            return Optional.of(new ValidationError("slot_must_be_empty"));
        }
        return Optional.empty();
    }
}
