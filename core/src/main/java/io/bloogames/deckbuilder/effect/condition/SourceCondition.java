package io.bloogames.deckbuilder.effect.condition;

import io.bloogames.deckbuilder.effect.context.SourceContext;
import io.bloogames.deckbuilder.effect.source.Source;
import io.bloogames.deckbuilder.effect.validation.ConditionValidator;
import io.bloogames.deckbuilder.error.ValidationError;

import java.util.Optional;

public interface SourceCondition<T extends Source> {
    Optional<ValidationError> check(SourceContext<? extends Source> ctx, ConditionValidator validator);
}
