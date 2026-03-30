package io.bloogames.deckbuilder.action;

import io.bloogames.deckbuilder.card.Battler;
import io.bloogames.deckbuilder.card.Slot;
import io.bloogames.deckbuilder.card.Tableau;

public class SwapBattlerCommand extends Command {
    private Tableau tableau;
    private Slot slot1;
    private Slot slot2;

    public SwapBattlerCommand(Tableau tableau, Slot slot1, Slot slot2) {
        this.tableau = tableau;
        this.slot1 = slot1;
        this.slot2 = slot2;
    }

    public void execute() {
        Battler battler1 = slot1.getBattler();
        Battler battler2 = slot2.getBattler();

        if (battler1 != null) {
            slot2.setBattler(battler1);
        }
        else {
            slot2.removeBattler();
        }

        if (battler2 != null) {
            slot1.setBattler(battler2);
        }
        else {
            slot1.removeBattler();
        }

        tableau.refresh();
    }
}
