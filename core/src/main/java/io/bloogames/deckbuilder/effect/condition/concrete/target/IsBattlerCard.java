package io.bloogames.deckbuilder.effect.condition.concrete.target;

import io.bloogames.deckbuilder.effect.condition.TargetCondition;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.target.concrete.CardTarget;
import io.bloogames.deckbuilder.effect.validation.ConditionValidator;
import io.bloogames.deckbuilder.error.ValidationError;
import io.bloogames.deckbuilder.model.BattlerCardModel;

import java.util.Optional;

public class IsBattlerCard implements TargetCondition<CardTarget> {
    @Override
    public Optional<ValidationError> check(TargetContext<CardTarget> ctx, ConditionValidator validator) {
        if (ctx.target().card() instanceof BattlerCardModel) {
            return Optional.empty();
        }

        return Optional.of(new ValidationError("must_be_battler_card"));
    }
}
