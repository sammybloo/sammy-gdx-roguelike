package io.bloogames.deckbuilder.effect.validation;

import io.bloogames.deckbuilder.effect.condition.SourceConditionList;
import io.bloogames.deckbuilder.effect.context.SourceContext;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.error.ValidationError;
import io.bloogames.deckbuilder.model.GameModel;

import java.util.Optional;

public class CardValidator {
    private final ConditionValidator conditionValidator = new ConditionValidator();

    public Optional<ValidationError> checkCardsCanBePlayed(GameModel game) {
        return Optional.empty();
    }

    @SuppressWarnings("unchecked")
    public Optional<ValidationError> checkCardCanBePlayed(SourceContext<? extends CardSource> context) {
        Optional<ValidationError> result = checkManaCondition(context);
        if (result.isPresent()) return result;

        return conditionValidator.checkSourceConditionsAreMet(
            (SourceConditionList<CardSource>) context.source().card().getSourceConditionList(),
            (SourceContext<CardSource>) context);
    }

    public <T extends Target> Optional<ValidationError> checkCardCanBePlayedOnTarget(
        CardSource source,
        TargetContext<T> context
    ) {
        return conditionValidator.checkTargetIsValid(source.card().getTargetSpec(), context);
    }

    public Optional<ValidationError> checkManaCondition(SourceContext<? extends CardSource> context) {
        if (context.game().getBattle().getParty(context.source().owner()).getLeader().getCurrentMana()
            >= context.source().card().getCurrentCost()) {
            return Optional.empty();
        }

        return Optional.of(new ValidationError("not_enough_mana"));
    }
}
