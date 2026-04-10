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

    public PartyModel getOwner(CardModel cardModel) {
        if (playerParty.hasCard(cardModel)) {
            return playerParty;
        } else if (enemyParty.hasCard(cardModel)) {
            return enemyParty;
        }
        return null;
    }
}
