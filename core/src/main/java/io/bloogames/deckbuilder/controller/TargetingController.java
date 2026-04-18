package io.bloogames.deckbuilder.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetOwnerType;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.effect.target.concrete.BattlerTarget;
import io.bloogames.deckbuilder.effect.target.concrete.CardTarget;
import io.bloogames.deckbuilder.effect.target.concrete.LeaderTarget;
import io.bloogames.deckbuilder.effect.target.concrete.SlotTarget;
import io.bloogames.deckbuilder.scene2d.HoverListener;
import io.bloogames.deckbuilder.ui.target.TargetState;
import io.bloogames.deckbuilder.ui.target.Targetable;
import io.bloogames.deckbuilder.view.BattlerView;
import io.bloogames.deckbuilder.view.CardView;
import io.bloogames.deckbuilder.view.PartyView;
import io.bloogames.deckbuilder.view.SlotView;
import io.bloogames.deckbuilder.view.event.ViewEvent;

public class TargetingController {
    private CardSource currentCard;
    private ObjectMap<Targetable, InputListener> listenerMap = new ObjectMap<>();

    public TargetingController(BattleController battleController, PartyView playerParty, PartyView enemyParty) {
        registerParty(battleController, playerParty);
        registerParty(battleController, enemyParty);
    }

    public void registerParty(BattleController battleController, PartyView party) {
        battleController.getEventBus().register(ViewEvent.CardStartEvent.class, e -> {
            currentCard = e.cardSource();
            LeaderTarget leaderTarget = new LeaderTarget(party.getLeader().getModel(), party.getModel().getParty());
            setValidity(battleController, party.getLeader(), leaderTarget);
            registerToPlayCard(battleController, party.getLeader(), leaderTarget);
            if (!battleController.getBattleState().canSelectCards() || party.getModel().getParty() != currentCard.owner()) {
                for (CardView card : party.getHand().getCardViews()) {
                    CardTarget cardTarget = new CardTarget(card.getModel(), party.getModel().getParty());
                    setValidity(battleController, card, cardTarget);
                    registerToPlayCard(battleController, card, cardTarget);
                }
            }

            for (SlotView slot : party.getTableau().getSlots()) {
                SlotTarget slotTarget = new SlotTarget(slot.getModel(), party.getModel().getParty());
                if (battleController.isValidTarget(currentCard, slotTarget)) {
                    slot.setTargetState(TargetState.VALID);
                }
                else {
                    slot.setTargetState(TargetState.INVALID);
                }
                setValidity(battleController, slot, slotTarget);
                registerToPlayCard(battleController, slot, slotTarget);

                if (slot.hasBattler()) {
                    BattlerView battler = slot.getBattler();
                    BattlerTarget battlerTarget = new BattlerTarget(battler.getModel(), party.getModel().getParty());
                    setValidity(battleController, battler, battlerTarget);
                    registerToPlayCard(battleController, battler, battlerTarget);
                }
            }
        });

        battleController.getEventBus().register(ViewEvent.CardPlayedEvent.class, e -> {
            listenerMap.forEach(entry -> {
                entry.key.setTargetState(TargetState.NOT_TARGETED);
                entry.key.setHovered(false);
                entry.key.actor().removeListener(entry.value);
            });

            listenerMap.clear();
        });

    }

    public void setValidity(BattleController battleController, Targetable targetable, Target target) {
        if (battleController.isValidTarget(currentCard, target)) {
            targetable.setTargetState(TargetState.VALID);
        }
        else {
            targetable.setTargetState(TargetState.INVALID);
        }
    }

    public void registerToPlayCard(BattleController battleController, Targetable targetable, Target target) {
        if (listenerMap.containsKey(targetable)) {
            return;
        }

        InputListener listener = new HoverListener(0f, 0f) {
                @Override
                public void onHoverStart(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    if (battleController.getBattleState().canTargetCards()) {
                        targetable.setHovered(true);
                    }
                }

                @Override
                public void onHoverEnd(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    if (battleController.getBattleState().canTargetCards()) {
                        targetable.setHovered(false);
                    }
                }

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (battleController.getBattleState().canTargetCards()) {
                        battleController.playCard(currentCard, target);
                    }
                }
            };
        targetable.actor().addListener(listener);
        listenerMap.put(targetable, listener);
    }
}
