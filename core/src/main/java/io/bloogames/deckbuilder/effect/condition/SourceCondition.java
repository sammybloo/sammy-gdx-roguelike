package io.bloogames.deckbuilder.effect.condition;

import io.bloogames.deckbuilder.effect.context.SourceContext;
import io.bloogames.deckbuilder.effect.source.EffectSource;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.effect.validation.ConditionValidator;
import io.bloogames.deckbuilder.error.ValidationError;

import java.util.Optional;

public interface SourceCondition<T extends EffectSource> {
    public Optional<ValidationError> check(SourceContext<? extends CardSource> ctx, ConditionValidator validator);
}
