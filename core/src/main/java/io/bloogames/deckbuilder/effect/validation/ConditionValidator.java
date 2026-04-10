package io.bloogames.deckbuilder.effect.validation;

import io.bloogames.deckbuilder.effect.context.SourceContext;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.effect.target.TargetOwnerType;
import io.bloogames.deckbuilder.effect.target.TargetSpec;
import io.bloogames.deckbuilder.error.ValidationError;
import io.bloogames.deckbuilder.screen.Battle;

import java.util.Optional;

public class ConditionValidator {
    public Optional<ValidationError> checkCardsCanBePlayed(Battle battle) {
        return Optional.empty();
    }

    public Optional<ValidationError> checkCardCanBePlayed(SourceContext<? extends CardSource> context) {
        Optional<ValidationError> result = checkManaCondition(context);

        var conditions = context.source().card().getSourceConditionList().getConditions();
        for (var c : conditions) {
            if (result.isPresent()) return result;
            result = c.check(context, this);
        }

        return result;
    }

    public Optional<ValidationError> checkCardCanBePlayedOnTarget(CardSource source, TargetContext<?> context) {
        Optional<ValidationError> result = checkCardAllowsTarget(source, context);
        if (result.isPresent()) return result;


        return Optional.empty();
    }

    public Optional<ValidationError> checkManaCondition(SourceContext<? extends CardSource> context) {
        if (context.source().caster().getCurrentMana() >= context.source().card().getCurrentCost()) {
            return Optional.empty();
        }

        return Optional.of(new ValidationError("not_enough_mana"));
    }

    public Optional<ValidationError> checkCardAllowsTarget(CardSource source, TargetContext<?> context) {
        TargetSpec targetSpec = source.card().getTargetSpec();
        if (!targetSpec.allows(context.target().type())) {
            return Optional.of(new ValidationError("wrong_target_type"));
        }

        if (targetSpec.ownerType() == TargetOwnerType.ANY) {
            return Optional.empty();
        }

        boolean isOwn = context.battle().getOwner(source.card()) == context.target().owner();
        if (targetSpec.ownerType() == TargetOwnerType.OWN && !isOwn) {
            return Optional.of(new ValidationError("target_must_be_own"));
        } else if (targetSpec.ownerType() == TargetOwnerType.OTHER && isOwn) {
            return Optional.of(new ValidationError("target_must_be_other"));
        }

        return Optional.empty();
    }
}
