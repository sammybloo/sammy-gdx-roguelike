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
import io.bloogames.deckbuilder.model.CardModel;

import java.util.Optional;

public class CardCoordinator {
    CardValidator cardValidator = new CardValidator();

    public void playCard(EffectExecutor executor, BattleModel battle, CardModel card, Target target) {
        Optional<ValidationError> result = cardValidator.checkCardsCanBePlayed(battle);
        if (result.isPresent()) {
            executor.emit(new CardFailedEvent(battle, card, target, result.get()));
            return;
        }

        CardSource source = new CardSource(card, battle.getOwner(card));
        SourceContext<CardSource> sourceContext = new SourceContext<>(battle, source);
        result = cardValidator.checkCardCanBePlayed(sourceContext);
        if (result.isPresent()) {
            executor.emit(new CardFailedEvent(battle, card, target, result.get()));
            return;
        }

        TargetContext<Target> targetContext = new TargetContext<>(battle, source, target);
        result = cardValidator.checkCardCanBePlayedOnTarget(source, targetContext);
        if (result.isPresent()) {
            executor.emit(new CardFailedEvent(battle, card, target, result.get()));
            return;
        }

        executor.enqueueImmediate(card.getEffect(), targetContext);
        executor.emit(new CardPlayedEvent(battle, card, source, target));
    }
}
