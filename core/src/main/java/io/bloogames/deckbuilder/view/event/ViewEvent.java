package io.bloogames.deckbuilder.view.event;
import io.bloogames.deckbuilder.effect.source.Source;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.error.ValidationError;
import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.model.*;
import io.bloogames.deckbuilder.ui.BattleState;
import io.bloogames.deckbuilder.ui.BattleViewState;

public sealed interface ViewEvent {
    record BattleStateEvent(BattleState oldState, BattleState newState) implements ViewEvent {}
    record BattleViewStateEvent(BattleViewState oldState, BattleViewState newState) implements ViewEvent {}
    record BattlerAddedEvent(SlotModel slot, BattlerModel battler) implements ViewEvent {}
    record BattlerSwappedEvent(TableauModel tableau, SlotModel slot1, SlotModel slot2) implements ViewEvent {}
    record CardFailedEvent(CardModel card, Target target, ValidationError error) implements ViewEvent {}
    record CardPlayedEvent(CardModel card, CardSource cardSource, Target target) implements ViewEvent {}
    record DamageDealtEvent(Source source, Damageable target, int amount) implements ViewEvent { }
}


