package io.bloogames.deckbuilder.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import io.bloogames.deckbuilder.command.ChooseTargetCommand;
import io.bloogames.deckbuilder.manager.CommandManager;
import io.bloogames.deckbuilder.view.Card;
import io.bloogames.deckbuilder.view.Hand;
import io.bloogames.deckbuilder.scene2d.HoverListener;

public interface HandController {

    void attach(Hand hand, Card card);
}
