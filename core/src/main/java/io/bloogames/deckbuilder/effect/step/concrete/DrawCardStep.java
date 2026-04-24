package io.bloogames.deckbuilder.effect.step.concrete;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.target.concrete.BattlePartyTarget;
import io.bloogames.deckbuilder.error.ValidationError;
import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.model.BattlePartyModel;
import io.bloogames.deckbuilder.model.CardModel;

public class DrawCardStep implements TargetStep<BattlePartyTarget> {

    @Override
    public void applyTarget(TargetContext<BattlePartyTarget> context) {
        BattlePartyModel battleParty = context.target().battleParty();
        if (battleParty.getHand().isFull()) {
            context.game().dispatch(new GameEvent.EffectFailedEvent(context.source(), context.target(), new ValidationError("hand_full")));
            return;
        }
        CardModel card = battleParty.getDeck().removeTopCard();
        battleParty.getHand().addCard(card);
        context.game().dispatch(new GameEvent.CardDrawnEvent(battleParty.getHand(), card));
    }
}
