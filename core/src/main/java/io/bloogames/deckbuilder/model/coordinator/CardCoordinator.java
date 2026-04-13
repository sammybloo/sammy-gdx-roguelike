package io.bloogames.deckbuilder.model.coordinator;

import io.bloogames.deckbuilder.effect.context.SourceContext;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.validation.CardValidator;
import io.bloogames.deckbuilder.error.ValidationError;
import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.model.BattleModel;
import io.bloogames.deckbuilder.ui.BattleState;

import java.util.Optional;

public class CardCoordinator {
    private final CardValidator cardValidator = new CardValidator();

    public boolean canPlayCard(BattleModel battle, CardSource source) {
        Optional<ValidationError> result = cardValidator.checkCardsCanBePlayed(battle);
        if (result.isPresent()) {
            return false;
        }

        SourceContext<CardSource> sourceContext = new SourceContext<>(battle, source);
        result = cardValidator.checkCardCanBePlayed(sourceContext);
        return result.isEmpty();
    }

    public boolean isValidTarget(BattleModel battle, CardSource source, Target target) {
        TargetContext<Target> targetContext = new TargetContext<>(battle, source, target);
        return cardValidator.checkCardCanBePlayedOnTarget(source, targetContext).isEmpty();
    }

    public Optional<ValidationError> playCard(BattleModel battle, CardSource source, Target target) {
        Optional<ValidationError> result = cardValidator.checkCardsCanBePlayed(battle);
        if (result.isPresent()) {
            return result;
        }

        SourceContext<CardSource> sourceContext = new SourceContext<>(battle, source);
        result = cardValidator.checkCardCanBePlayed(sourceContext);
        if (result.isPresent()) {
            return result;
        }

        TargetContext<Target> targetContext = new TargetContext<>(battle, source, target);
        result = cardValidator.checkCardCanBePlayedOnTarget(source, targetContext);
        if (result.isPresent()) {
            return result;
        }

        battle.getExecutor().enqueueImmediate(source.card().getEffect(), targetContext);
        battle.dispatch(new GameEvent.CardPlayedEvent(battle, source.card(), source, target));
        battle.setState(BattleState.CARD_ACTIVATING);
        return Optional.empty();
    }
}
