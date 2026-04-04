package io.bloogames.deckbuilder.command;

import io.bloogames.deckbuilder.model.BattlerModel;
import io.bloogames.deckbuilder.model.SlotModel;
import io.bloogames.deckbuilder.model.TableauModel;
import io.bloogames.deckbuilder.screen.BattleScreen;

public class SwapBattlerCommand implements Command {

    private final TableauModel tableauModel;
    private final SlotModel slot1;
    private final SlotModel slot2;

    public SwapBattlerCommand(TableauModel tableau, SlotModel slot1, SlotModel slot2) {
        this.tableauModel = tableau;
        this.slot1 = slot1;
        this.slot2 = slot2;
    }

    @Override
    public void execute(BattleScreen battleScreen) {
        BattlerModel battler1 = slot1.getBattler();
        BattlerModel battler2 = slot2.getBattler();

        slot1.setBattler(battler2);
        slot2.setBattler(battler1);

        battleScreen.getPlayerTableau().refresh();
        battleScreen.getEnemyTableau().refresh();
    }
}
