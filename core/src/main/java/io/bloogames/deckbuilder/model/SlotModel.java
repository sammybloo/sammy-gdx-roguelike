package io.bloogames.deckbuilder.model;

public class SlotModel {
    private BattlerModel battler;
    private LeaderModel participant;

    public SlotModel(LeaderModel participant) {
        this.participant = participant;
    }

    public BattlerModel getBattler() {
        return battler;
    }

    public LeaderModel getParticipant() {
        return participant;
    }

    public void setBattler(BattlerModel battler) {
        this.battler = battler;
    }
}
