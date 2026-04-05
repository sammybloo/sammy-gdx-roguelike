package io.bloogames.deckbuilder.view;

import io.bloogames.deckbuilder.model.PartyModel;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.View;

public abstract class PartyView extends ResizableGroup implements View {

    private PartyModel model;
    private LeaderView leader;
    private HandView hand;
    private TableauView tableau;

    public static final float WIDTH = 1920;
    public static final float HEIGHT = 540;

    public PartyView(PartyModel model) {
        super(WIDTH, HEIGHT);
        this.model = model;
    }

    public PartyModel getModel() {
        return model;
    }

    public void setModel(PartyModel model) {
        this.model = model;
    }

    public LeaderView getLeader() {
        return leader;
    }

    public void setLeader(LeaderView leader) {
        this.leader = leader;
    }

    public HandView getHand() {
        return hand;
    }

    public void setHand(HandView hand) {
        this.hand = hand;
    }

    public TableauView getTableau() {
        return tableau;
    }

    public void setTableau(TableauView tableau) {
        this.tableau = tableau;
    }

    @Override
    public void update() {
        leader.update();
        hand.update();
        tableau.update();
    }
}
