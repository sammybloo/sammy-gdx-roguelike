package io.bloogames.deckbuilder.model.coordinator;

import io.bloogames.deckbuilder.effect.context.SourceContext;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.event.CardFailedEvent;
import io.bloogames.deckbuilder.effect.event.CardPlayedEvent;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.validation.CardValidator;
import io.bloogames.deckbuilder.error.ValidationError;
import io.bloogames.deckbuilder.model.BattleModel;

import java.util.Optional;

public class CardCoordinator {
    private final CardValidator cardValidator = new CardValidator();
    private final EffectExecutor executor;
    private final BattleModel battle;

    public CardCoordinator(EffectExecutor executor, BattleModel battle) {
        this.executor = executor;
        this.battle = battle;
    }

    public boolean canPlayCard(CardSource source) {
        Optional<ValidationError> result = cardValidator.checkCardsCanBePlayed(battle);
        if (result.isPresent()) {
            return false;
        }

        SourceContext<CardSource> sourceContext = new SourceContext<>(battle, source);
        result = cardValidator.checkCardCanBePlayed(sourceContext);
        return result.isEmpty();
    }

    public boolean isValidTarget(CardSource source, Target target) {
        TargetContext<Target> targetContext = new TargetContext<>(battle, source, target);
        return cardValidator.checkCardCanBePlayedOnTarget(source, targetContext).isEmpty();
    }

    public void playCard(CardSource source, Target target) {
        Optional<ValidationError> result = cardValidator.checkCardsCanBePlayed(battle);
        if (result.isPresent()) {
            executor.emit(new CardFailedEvent(battle, source.card(), target, result.get()));
            return;
        }

        SourceContext<CardSource> sourceContext = new SourceContext<>(battle, source);
        result = cardValidator.checkCardCanBePlayed(sourceContext);
        if (result.isPresent()) {
            executor.emit(new CardFailedEvent(battle, source.card(), target, result.get()));
            return;
        }

        TargetContext<Target> targetContext = new TargetContext<>(battle, source, target);
        result = cardValidator.checkCardCanBePlayedOnTarget(source, targetContext);
        if (result.isPresent()) {
            executor.emit(new CardFailedEvent(battle, source.card(), target, result.get()));
            return;
        }

        executor.enqueueImmediate(source.card().getEffect(), targetContext);
        executor.emit(new CardPlayedEvent(battle, source.card(), source, target));
    }
}
