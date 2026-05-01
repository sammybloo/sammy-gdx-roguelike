package io.bloogames.deckbuilder.model.coordinator;

import com.badlogic.gdx.Gdx;
import io.bloogames.deckbuilder.effect.context.SourceContext;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.validation.CardValidator;
import io.bloogames.deckbuilder.error.ValidationError;
import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.model.CardModel;
import io.bloogames.deckbuilder.model.CardZone;
import io.bloogames.deckbuilder.model.GameModel;
import io.bloogames.deckbuilder.ui.BattleState;

import java.util.Optional;

public class CardCoordinator {
    private final CardValidator cardValidator = new CardValidator();
    private final GameModel game;

    public CardCoordinator(GameModel game) {
        this.game = game;
    }

    public Optional<ValidationError> canPlayCard(CardSource source) {
        Optional<ValidationError> result = cardValidator.checkCardsCanBePlayed(game);
        if (result.isPresent()) {
            return result;
        }

        SourceContext<CardSource> sourceContext = new SourceContext<>(game, source);
        return cardValidator.checkCardCanBePlayed(sourceContext);
    }

    public boolean isValidTarget(CardSource source, Target target) {
        TargetContext<Target> targetContext = new TargetContext<>(game, source, target);
        return cardValidator.checkCardCanBePlayedOnTarget(source, targetContext).isEmpty();
    }

    public Optional<ValidationError> playCard(CardSource source, Target target) {
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

        game.getBattle().getParty(source.owner()).getLeader().spendMana(game.getBattle(), source.card().getCurrentCost());

        game.getBattle().getOwner(source.card()).getHand().removeCard(source.card());
        game.getExecutor().enqueueImmediate(source.card().getEffect(), targetContext);
        game.dispatch(new GameEvent.CardPlayedEvent(source.card(), source, target));
        game.getBattle().setState(BattleState.CARD_ACTIVATING);
        return Optional.empty();
    }

    public void moveCard(CardModel card, CardZone from, CardZone to) {
        if (!from.contains(card)) {
            Gdx.app.error(CardCoordinator.class.getSimpleName(),
                "Tried to move card between zones, but card does not exist in from zone.");
            return;
        }
        from.removeCard(card);
        to.addCard(card);
        game.dispatch(new GameEvent.CardMovedEvent(card, from, to));
    }
}
