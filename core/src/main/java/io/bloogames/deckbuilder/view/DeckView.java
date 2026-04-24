package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import io.bloogames.deckbuilder.manager.CardManager;
import io.bloogames.deckbuilder.model.ActionCardModel;
import io.bloogames.deckbuilder.model.CardModel;
import io.bloogames.deckbuilder.model.DeckModel;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.scene2d.ResizableSettings;
import io.bloogames.deckbuilder.ui.View;
import io.bloogames.deckbuilder.ui.color.Tint;
import io.bloogames.deckbuilder.vfx.VFXManager;
import io.bloogames.deckbuilder.vfx.concrete.FlipCardFaceupVisualEffect;

public class DeckView extends ResizableGroup implements View {

    public static final float WIDTH = 200f;
    public static final float HEIGHT = 300f;

    private Array<CardView> cardViews = new Array<>();
    private DeckModel deckModel;

    public DeckView(DeckModel deckModel, boolean rotateCards) {
        super(WIDTH, HEIGHT);
        this.deckModel = deckModel;
        for (int i = 0; i < deckModel.getCards().size; i++) {
            CardView card = CardManager.INSTANCE.getCard(deckModel.getCards().get(i));
            cardViews.add(card);
            if (rotateCards) {
                card.setRotation(180f);
            }
            if (i < deckModel.getCards().size - 2) {
                float random = MathUtils.random(0.5f, 1f);
                card.addTint(new Tint("deckview_randomise", new Color(random, random, random, 1f).toFloatBits()));
            }
            card.rotateBy(MathUtils.random(-0.5f, 0.5f));
            register(card, new ResizableSettings(CardView.WIDTH / 2, CardView.HEIGHT / 2).offset(i * 0.2f, i * 1f));
        }
    }

    public CardView getCard(CardModel cardModel) {
        for (CardView card : cardViews) {
            if (card.getModel() == cardModel) {
                return card;
            }
        }
        return null;
    }

    public void removeCard(CardView card) {
        cardViews.removeValue(card, true);
        removeActor(card);
        fixTopCards();
    }

    public void fixTopCards() {
        for (int i = Math.max(0, cardViews.size - 2); i < cardViews.size; i++) {
            cardViews.get(i).removeTint("deckview_randomise");
        }
    }

    @Override
    public void sync() {

    }
}
