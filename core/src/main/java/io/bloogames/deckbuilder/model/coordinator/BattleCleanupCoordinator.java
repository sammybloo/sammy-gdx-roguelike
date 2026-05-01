package io.bloogames.deckbuilder.model.coordinator;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.target.concrete.BattlerTarget;
import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.model.BattlerModel;
import io.bloogames.deckbuilder.model.GameModel;
import io.bloogames.deckbuilder.model.SlotModel;
import io.bloogames.deckbuilder.model.TableauModel;
import io.bloogames.deckbuilder.model.aura.Aura;
import io.bloogames.deckbuilder.model.death.Death;
import io.bloogames.deckbuilder.model.death.DeathPreventer;

import java.util.Optional;

public class BattleCleanupCoordinator {
    private final GameModel game;

    public BattleCleanupCoordinator(GameModel game) {
        this.game = game;
    }

    public void beforeEachStep() {
        generateAuras();
        updateStats();
    }

    public boolean beforePhase() {
        generateAuras();
        updateStats();
        return checkForDeaths();
    }

    public void afterCardPlayed() {
        generateAuras();
        updateStats();
        checkForDeaths();
    }

    public void generateAuras() {
        game.generateAuras();
    }

    public void updateStats() {
        updateStatsForTableau(game.getBattle().getPlayerParty().getTableau());
        updateStatsForTableau(game.getBattle().getEnemyParty().getTableau());
    }

    private void updateStatsForTableau(TableauModel tableau) {
        Array<Aura> auras = game.getAllAuras();

        for (SlotModel slot : tableau.getSlots()) {
            if (slot.hasBattler()) {
                BattlerTarget battlerTarget = new BattlerTarget(slot.getBattler());
                slot.getBattler().getStats().clearModifiers();
                for (Aura aura : auras) {
                    aura.onCalculateStats(game, battlerTarget);
                }
            }
        }
    }

    public boolean checkForDeaths() {
        boolean deathOccurred = false;
        if (game.getBattle().getPlayerParty().getLeader().getCurrentHealth() <= 0) {
            // TODO end battle
        }
        if (game.getBattle().getEnemyParty().getLeader().getCurrentHealth() <= 0) {
            // TODO end battle
        }

        deathOccurred = checkDeathsForTableau(game.getBattle().getEnemyParty().getTableau());
        deathOccurred = deathOccurred || checkDeathsForTableau(game.getBattle().getPlayerParty().getTableau());
        return deathOccurred;
    }

    private boolean checkDeathsForTableau(TableauModel tableau) {
        Array<Aura> auras = game.getAllAuras();
        boolean deathOccurred = false;

        for (SlotModel slot : tableau.getSlots()) {
            if (slot.hasBattler()) {
                BattlerModel battler = slot.getBattler();
                if (battler.getMaxHealth() <= 0) {
                    battler.markForDeath(new Death(Death.Type.NO_MAX_HEALTH));
                }
                else if (battler.getCurrentHealth() <= 0) {
                    battler.markForDeath(new Death(Death.Type.DAMAGE));
                }

                while (battler.isMarkedForDeath()) {
                    Death death = battler.popDeath();
                    BattlerTarget battlerTarget = new BattlerTarget(battler);

                    for (Aura aura : auras) {
                        aura.beforeDeath(game, battlerTarget, death);
                    }

                    Optional<DeathPreventer> preventerOptional = death.getPreventer();
                    if (preventerOptional.isPresent()) {
                        preventerOptional.get().onPrevent(battlerTarget, death);
                        continue;
                    }

                    slot.setBattler(null);

                    // For now, stolen cards go back to their owner
                    switch (death.getNextLocation()) {
                        case NOWHERE -> {
                        }
                        case DISCARD_PILE -> {
                            game.getBattle().getParty(battler.getCardModel().getOwnership().getOriginalOwner())
                                .getDiscardPile().addCard(battler.getCardModel());
                        }
                    }

                    game.dispatch(new GameEvent.BattlerDiedEvent(slot, battler, death));

                    for (Aura aura : auras) {
                        aura.afterDeath(game, battlerTarget, death);
                    }

                    deathOccurred = true;
                    break;

                }
            }
        }
        return deathOccurred;
    }
}
