package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.controller.HandController;
import io.bloogames.deckbuilder.manager.CardManager;
import io.bloogames.deckbuilder.model.CardModel;
import io.bloogames.deckbuilder.model.HandModel;
import io.bloogames.deckbuilder.scene2d.FannedGroup;
import io.bloogames.deckbuilder.ui.View;

public class HandView extends FannedGroup implements View {

    private final HandModel model;
    private final ObjectMap<CardModel, CardView> cardViews = new ObjectMap<>();
    private final Array<HandController> interactionControllers;

    public HandView(HandModel model, FannedGroup.FanSettings fanSettings, HandController... interactionControllers) {
        super(fanSettings);
        this.model = model;
        this.interactionControllers = new Array<>(interactionControllers);
        setTouchable(Touchable.childrenOnly);
        update();
    }

    public void addCard(CardModel cardModel) {
        if (cardViews.containsKey(cardModel)) return;

        CardView cardView = CardManager.INSTANCE.getCard(cardModel);
        cardViews.put(cardModel, cardView);
        cardView.setSize(fannableWidth, fannableHeight);
        addActor(cardView);
        for (var controller : interactionControllers) {
            controller.attach(this, cardView);
        }
        fan();
    }

    public void removeCard(CardModel cardModel) {
        CardView card = cardViews.remove(cardModel);
        if (card != null) {
            unselectActor(card);
            removeActor(card);
            removeFannable(card);
        }

        fan();
    }

    public boolean containsCard(CardModel card) {
        return cardViews.containsKey(card);
    }

    public HandModel getModel() {
        return model;
    }

    public void layout() {
        setFannableSize(getHeight() * 0.67f, getHeight());
        fan();
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        layout();
    }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        super.setBounds(x, y, width, height);
        layout();
    }

    @Override
    public void update() {
        Array<Actor> orderedCards = new Array<>();
        Array<CardModel> models = model.getCards();
        for (int i = 0; i < models.size; i++) {
            CardModel cardModel = models.get(i);
            if (!cardViews.containsKey(cardModel)) {
                addCard(cardModel);
            }
            orderedCards.add(cardViews.get(cardModel));
        }

        cardViews.forEach((entry) -> {
            if (!orderedCards.contains(entry.value, true)) {
                removeCard(entry.key);
            }
        });

        setFannables(orderedCards);
    }
}
