package io.bloogames.deckbuilder.model.coordinator;

import io.bloogames.deckbuilder.effect.context.SourceContext;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.validation.CardValidator;
import io.bloogames.deckbuilder.error.ValidationError;
import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.model.GameModel;
import io.bloogames.deckbuilder.ui.BattleState;

import java.util.Optional;

public class CardCoordinator {
    private final CardValidator cardValidator = new CardValidator();

    public Optional<ValidationError> canPlayCard(GameModel game, CardSource source) {
        Optional<ValidationError> result = cardValidator.checkCardsCanBePlayed(game);
        if (result.isPresent()) {
            return result;
        }

        SourceContext<CardSource> sourceContext = new SourceContext<>(game, source);
        return cardValidator.checkCardCanBePlayed(sourceContext);
    }

    public boolean isValidTarget(GameModel game, CardSource source, Target target) {
        TargetContext<Target> targetContext = new TargetContext<>(game, source, target);
        return cardValidator.checkCardCanBePlayedOnTarget(source, targetContext).isEmpty();
    }

    public Optional<ValidationError> playCard(GameModel game, CardSource source, Target target) {
        Optional<ValidationError> result = cardValidator.checkCardsCanBePlayed(game);
        if (result.isPresent()) {
            return result;
        }

        SourceContext<CardSource> sourceContext = new SourceContext<>(game, source);
        result = cardValidator.checkCardCanBePlayed(sourceContext);
        if (result.isPresent()) {
            return result;
        }

        TargetContext<Target> targetContext = new TargetContext<>(game, source, target);
        result = cardValidator.checkCardCanBePlayedOnTarget(source, targetContext);
        if (result.isPresent()) {
            return result;
        }

        source.owner().getLeader().spendMana(game.getBattle(), source.card().getCurrentCost());

        game.getBattle().getOwner(source.card()).getHand().removeCard(source.card());
        game.getExecutor().enqueueImmediate(source.card().getEffect(), targetContext);
        game.dispatch(new GameEvent.CardPlayedEvent(source.card(), source, target));
        game.getBattle().setState(BattleState.CARD_ACTIVATING);
        return Optional.empty();
    }
}
