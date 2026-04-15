package io.bloogames.deckbuilder.controller;

import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.error.ValidationError;
import io.bloogames.deckbuilder.model.*;
import io.bloogames.deckbuilder.model.coordinator.ViewEventBus;
import io.bloogames.deckbuilder.view.event.ViewEvent;

import java.util.Optional;

public class BattleController {
    private final GameModel game;
    private final ViewEventBus eventBus;
    private final BattleStateController battleState;

    public BattleController(GameModel game) {
        this.game = game;
        this.eventBus = new ViewEventBus(game.getEventDispatcher());
        this.battleState = new BattleStateController(eventBus);
    }

    public GameModel getGame() {
        return game;
    }

    public BattleModel getBattle() {
        return game.getBattle();
    }

    public ViewEventBus getEventBus() {
        return eventBus;
    }

    public BattleStateController getBattleState() {
        return battleState;
    }

    public void startPlayCard(CardModel card, PartyModel owner) {
        CardSource cardSource = new CardSource(card, owner);
        Optional<ValidationError> result = getBattle().getCardCoordinator().canPlayCard(game, cardSource);

        result.ifPresentOrElse(error -> getEventBus().dispatch(new ViewEvent.CardStartFailedEvent(cardSource, error)),
            () -> getEventBus().dispatch(new ViewEvent.CardStartEvent(cardSource)));
    }

    public void playCard(CardSource cardSource, Target target) {
        Optional<ValidationError> result = getBattle().getCardCoordinator().playCard(game, cardSource, target);
        result.ifPresent(error -> getEventBus().dispatch(new ViewEvent.CardFailedEvent(cardSource, target, error)));
    }

    public boolean isValidTarget(CardSource cardSource, Target target) {
        return getBattle().getCardCoordinator().isValidTarget(game, cardSource, target);
    }

    public void swapSlots(TableauModel tableau, SlotModel slot1, SlotModel slot2) {
        tableau.swapBattlers(getBattle(), slot1, slot2);
    }
}
