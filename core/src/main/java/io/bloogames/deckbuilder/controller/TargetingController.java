package io.bloogames.deckbuilder.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.concrete.BattlerTarget;
import io.bloogames.deckbuilder.effect.target.concrete.CardTarget;
import io.bloogames.deckbuilder.effect.target.concrete.LeaderTarget;
import io.bloogames.deckbuilder.effect.target.concrete.SlotTarget;
import io.bloogames.deckbuilder.ui.scene2d.HoverListener;
import io.bloogames.deckbuilder.ui.target.TargetState;
import io.bloogames.deckbuilder.ui.target.Targetable;
import io.bloogames.deckbuilder.vfx.VFXManager;
import io.bloogames.deckbuilder.vfx.concrete.DisappearEffect;
import io.bloogames.deckbuilder.view.*;
import io.bloogames.deckbuilder.view.ViewEvent;

public class TargetingController {
    private final ObjectMap<Targetable, InputListener> listenerMap = new ObjectMap<>();
    private final SelectedCardView selectedCardView;

    public TargetingController(BattleController battleController, PartyView playerParty, PartyView enemyParty, SelectedCardView selectedCardView) {
        this.selectedCardView = selectedCardView;
        registerParties(battleController, playerParty, enemyParty);
    }

    public void beginTargeting(CardSource cardSource, BattleController battleController, PartyView playerParty, PartyView enemyParty) {
        if (selectedCardView.getSelectedCardSource() != null) {
            selectedCardView.removeCard().remove();
        }
        selectedCardView.setCard(cardSource);

        onCardStart(battleController, playerParty);
        onCardStart(battleController, enemyParty);
    }

    public void cancelTargeting() {
        listenerMap.forEach(entry -> {
            entry.key.setTargetState(TargetState.NOT_TARGETED);
            entry.key.setHovered(false);
            entry.key.actor().removeListener(entry.value);
        });
        listenerMap.clear();
        if (selectedCardView.getSelectedCardSource() != null) {
            VFXManager.INSTANCE.addEffect(new DisappearEffect(selectedCardView.removeCard()));
        }
    }

    public void registerParties(BattleController battleController, PartyView playerParty, PartyView enemyParty) {
        battleController.getEventBus().register(ViewEvent.CardStartEvent.class, e -> {
            beginTargeting(e.cardSource(), battleController, playerParty, enemyParty);
        });

        battleController.getEventBus().register(ViewEvent.CardPlayedEvent.class, e -> {
            cancelTargeting();
        });
    }

    public void onCardStart(BattleController battleController, PartyView party) {
        LeaderTarget leaderTarget = new LeaderTarget(party.getLeader().getModel());
        setValidity(battleController, party.getLeader(), leaderTarget);
        registerToPlayCard(battleController, party.getLeader(), leaderTarget);
        if (!battleController.getBattleState().canSelectCards() || party.getModel().getOwnership().getCurrentOwner() != selectedCardView.getSelectedCardSource().owner()) {
            for (CardView card : party.getHand().getCardViews()) {
                CardTarget cardTarget = new CardTarget(card.getModel());
                setValidity(battleController, card, cardTarget);
                registerToPlayCard(battleController, card, cardTarget);
            }
        }

        for (SlotView slot : party.getTableau().getSlots()) {
            SlotTarget slotTarget = new SlotTarget(slot.getModel());
            if (battleController.isValidTarget(selectedCardView.getSelectedCardSource(), slotTarget)) {
                slot.setTargetState(TargetState.VALID);
            } else {
                slot.setTargetState(TargetState.INVALID);
            }
            setValidity(battleController, slot, slotTarget);
            registerToPlayCard(battleController, slot, slotTarget);

            if (slot.hasBattler()) {
                BattlerView battler = slot.getBattler();
                BattlerTarget battlerTarget = new BattlerTarget(battler.getModel());
                setValidity(battleController, battler, battlerTarget);
                registerToPlayCard(battleController, battler, battlerTarget);
            }
        }
    }

    public void setValidity(BattleController battleController, Targetable targetable, Target target) {
        if (battleController.isValidTarget(selectedCardView.getSelectedCardSource(), target)) {
            targetable.setTargetState(TargetState.VALID);
        } else {
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
                    battleController.playCard(selectedCardView.getSelectedCardSource(), target);
                }
            }
        };
        targetable.actor().addListener(listener);
        listenerMap.put(targetable, listener);
    }
}
