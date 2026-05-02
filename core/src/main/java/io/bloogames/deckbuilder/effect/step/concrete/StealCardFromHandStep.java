package io.bloogames.deckbuilder.effect.step.concrete;

import com.badlogic.gdx.Gdx;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.target.concrete.CardTarget;
import io.bloogames.deckbuilder.model.BattlePartyModel;
import io.bloogames.deckbuilder.model.CardZone;

import java.util.Optional;

public class StealCardFromHandStep implements TargetStep<CardTarget> {

    private final CardZone.Type destination;

    public StealCardFromHandStep(CardZone.Type destination) {
        this.destination = destination;
    }

    @Override
    public void applyTarget(TargetContext<CardTarget> context) {
        BattlePartyModel sourceParty = context.game().getBattle().getParty(context.source().owner());
        BattlePartyModel targetParty = context.game().getBattle().getParty(context.target().owner());
        Optional<CardZone> cardZoneOptional = targetParty.getCardZone(context.target().card());
        cardZoneOptional.ifPresentOrElse((cardZone) -> {
                context.game().getBattle().getCardCoordinator()
                    .moveCard(context.target().card(), cardZone, sourceParty.getCardZone(destination));
            },
            () -> Gdx.app.error(StealCardFromHandStep.class.getSimpleName(),
                "Tried to steal card from hand, but the party didn't own the card."));
    }
}
