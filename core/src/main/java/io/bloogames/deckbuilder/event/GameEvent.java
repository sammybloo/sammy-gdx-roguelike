package io.bloogames.deckbuilder.event;

import io.bloogames.deckbuilder.effect.source.Source;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.model.*;
import io.bloogames.deckbuilder.ui.BattleState;

public sealed interface GameEvent {
    record BattlerAddedEvent(SlotModel slot, BattlerModel battler)
        implements GameEvent {
    }

    record BattlersSwappedEvent(TableauModel tableau, SlotModel slot1, SlotModel slot2)
        implements GameEvent {
    }

    record BattleStateEvent(BattleState oldState, BattleState newState)
        implements GameEvent {
    }

    record CardPlayedEvent(CardModel card, CardSource cardSource, Target target)
        implements GameEvent {
    }

    record DamageDealtEvent(Source source, Damageable target, int amount)
        implements GameEvent {
    }
}
