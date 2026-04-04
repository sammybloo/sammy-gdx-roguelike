package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.controller.HandController;
import io.bloogames.deckbuilder.model.CardModel;
import io.bloogames.deckbuilder.model.HandModel;
import io.bloogames.deckbuilder.scene2d.FannedGroup;

public class Hand extends FannedGroup {

    private final HandModel model;
    private final ObjectMap<CardModel, Card> cardActors = new ObjectMap<>();
    private final Array<HandController> interactionControllers;

    public Hand(HandModel model, FannedGroup.FanSettings fanSettings, HandController... interactionControllers) {
        super(fanSettings);
        this.model = model;
        this.interactionControllers = new Array<>(interactionControllers);
    }

    public boolean addCard(Card card) {
        CardModel cardModel = card.getModel();
        if (!model.addCard(cardModel)) return false;
        if (cardActors.containsKey(cardModel)) return false;

        cardActors.put(cardModel, card);
        addActor(card);
        for (var controller : interactionControllers) {
            controller.attach(this, card);
        }
        fan();
        return true;
    }

    public boolean removeCard(CardModel cardModel) {
        if (!model.removeCard(cardModel)) return false;

        Card card = cardActors.remove(cardModel);
        if (card != null) {
            removeActor(card);
        }

        fan();
        return true;
    }

    public Card getCard(CardModel cardModel) {
        return cardActors.get(cardModel);
    }

    public boolean contains(CardModel cardModel) {
        return model.contains(cardModel);
    }

    public void leaveTemporarily(Card card) {
        clearSelected();
        removeActor(card);
        fan();
    }

    public void returnCard(Card card) {
        if (card == null) return;

        Vector2 stageCoords = card.localToStageCoordinates(new Vector2());
        addActorAt(model.indexOf(card.getModel()), card);
        Vector2 localCoords = stageToLocalCoordinates(stageCoords);
        card.setPosition(localCoords.x, localCoords.y);
        fan();
    }

    public HandModel getModel() {
        return model;
    }
}
