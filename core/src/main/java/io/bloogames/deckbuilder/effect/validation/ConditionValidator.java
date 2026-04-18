package io.bloogames.deckbuilder.effect.validation;

import io.bloogames.deckbuilder.effect.condition.SourceConditionList;
import io.bloogames.deckbuilder.effect.condition.TargetCondition;
import io.bloogames.deckbuilder.effect.condition.TargetConditionList;
import io.bloogames.deckbuilder.effect.context.SourceContext;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.source.Source;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetOwnerType;
import io.bloogames.deckbuilder.effect.target.TargetSpec;
import io.bloogames.deckbuilder.error.ValidationError;

import java.util.Optional;

public class ConditionValidator {
    public <T extends Source> Optional<ValidationError> checkSourceConditionsAreMet(SourceConditionList<T> conditionList,
                                                                                    SourceContext<T> context) {
        for (var c : conditionList.getConditions()) {
            Optional<ValidationError> result = c.check(context, this);
            if (result.isPresent()) return result;
        }

        return Optional.empty();
    }

    public Optional<ValidationError> checkTargetIsValid(TargetSpec targetSpec, TargetContext<?> context) {
        Optional<ValidationError> result = checkTargetAgainstTargetSpec(targetSpec, context);
        if (result.isPresent()) return result;

        return checkTargetConditionsAreMet(targetSpec.getConditionList(), context);
    }

    @SuppressWarnings("unchecked")
    private Optional<ValidationError> checkTargetConditionsAreMet(TargetConditionList conditionList,
                                                                  TargetContext<?> context) {
        for (var c : conditionList.getConditions(context.target().type())) {

            Optional<ValidationError> result = ((TargetCondition<Target>) c).check((TargetContext<Target>) context, this);
            if (result.isPresent()) return result;
        }

        return Optional.empty();
    }

    private Optional<ValidationError> checkTargetAgainstTargetSpec(TargetSpec targetSpec,
                                                                   TargetContext<?> context) {
        if (!targetSpec.allows(context.target().type())) {
            return Optional.of(new ValidationError("wrong_target_type"));
        }

        if (targetSpec.ownerType() == TargetOwnerType.ANY) {
            return Optional.empty();
        }

        boolean isOwn = context.source().owner() == context.target().owner();
        if (targetSpec.ownerType() == TargetOwnerType.OWN && !isOwn) {
            return Optional.of(new ValidationError("target_must_be_own"));
        } else if (targetSpec.ownerType() == TargetOwnerType.OTHER && isOwn) {
            return Optional.of(new ValidationError("target_must_be_other"));
        }

        return Optional.empty();
    }
}
