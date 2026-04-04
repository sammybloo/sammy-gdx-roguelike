package io.bloogames.deckbuilder.model;

public class BattleModel {
    private PartyModel playerParty;
    private PartyModel enemyParty;

    public BattleModel(PartyModel playerParty, PartyModel enemyParty) {
        this.playerParty = playerParty;
        this.enemyParty = enemyParty;
    }

    public PartyModel getPlayerParty() {
        return playerParty;
    }

    public PartyModel getEnemyParty() {
        return enemyParty;
    }
}
