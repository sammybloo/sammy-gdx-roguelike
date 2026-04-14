package io.bloogames.deckbuilder.effect.target.concrete;

import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.BattlePartyModel;
import io.bloogames.deckbuilder.model.TableauModel;

public class TableauTarget implements Target {

    private BattlePartyModel owner;
    private TableauModel tableau;

    public TableauTarget(BattlePartyModel owner, TableauModel tableau) {
        this.owner = owner;
        this.tableau = tableau;
    }

    @Override
    public BattlePartyModel owner() {
        return owner;
    }

    @Override
    public TargetType type() {
        return TargetType.TABLEAU;
    }

    public TableauModel tableau() {
        return tableau;
    }
}
