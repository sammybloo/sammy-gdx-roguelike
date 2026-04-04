package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.command.ChooseTargetCommand;
import io.bloogames.deckbuilder.manager.CommandManager;
import io.bloogames.deckbuilder.scene2d.FannedGroup;
import io.bloogames.deckbuilder.scene2d.HoverListener;

public class Hand extends FannedGroup {

    private final Array<Card> cards;
    private final int maxSize;

    public Hand(int maxSize) {
        super(new FanSettings(0.3f, 80f, 16f, 0.5f, 0.8f, 130f, 0.3f, Card.WIDTH, Card.HEIGHT));
        this.cards = new Array<>();
        this.maxSize = maxSize;
    }

    public void addCard(Card card) {
        if (cards.size >= maxSize) return;
        cards.add(card);
        addActor(card);
        addHoverLogic(card);
        fan();
    }

    // Lets the actor leave visually without leaving logically
    public void leaveHandTemporarily(Card card) {
        clearSelected();
        removeActor(card);
        fan();
    }

    public void returnCard(Card card) {
        Vector2 stageCoords = card.localToStageCoordinates(new Vector2());
        addActorAt(cards.indexOf(card, true), card);
        Vector2 localCoords = stageToLocalCoordinates(stageCoords);
        card.setPosition(localCoords.x, localCoords.y);
        addHoverLogic(card);
        fan();
    }

    public void addHoverLogic(Card card) {
        card.addListener(new HoverListener(0f) {
            @Override
            public void onHoverStart(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                setSelectedActor(card);
            }

            @Override
            public void onHoverEnd(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                clearSelected();
            }

            @Override
            public void clicked (InputEvent event, float x, float y) {
                CommandManager.INSTANCE.processImmediately(new ChooseTargetCommand(card));
            }
        });
    }
}
