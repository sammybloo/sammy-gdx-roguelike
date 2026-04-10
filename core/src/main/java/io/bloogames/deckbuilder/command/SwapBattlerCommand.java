package io.bloogames.deckbuilder.command;

import io.bloogames.deckbuilder.model.BattleModel;
import io.bloogames.deckbuilder.model.BattlerModel;
import io.bloogames.deckbuilder.model.SlotModel;

public class SwapBattlerCommand implements Command {

    private final SlotModel slot1;
    private final SlotModel slot2;

    public SwapBattlerCommand(SlotModel slot1, SlotModel slot2) {
        this.slot1 = slot1;
        this.slot2 = slot2;
    }

    @Override
    public void execute(BattleModel battle) {
        BattlerModel battler1 = slot1.getBattler();
        BattlerModel battler2 = slot2.getBattler();

        slot1.setBattler(battler2);
        slot2.setBattler(battler1);
    }
}
