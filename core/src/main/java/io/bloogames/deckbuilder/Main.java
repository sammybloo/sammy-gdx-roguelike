package io.bloogames.deckbuilder;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.screen.BattleScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private SpriteBatch batch;
    private FitViewport viewport;

    public SpriteBatch getBatch() {
        return batch;
    }

    public FitViewport getViewport() {
        return viewport;
    }

    @Override
    public void create() {
        this.viewport = new FitViewport(1920, 1080);
        this.batch = new SpriteBatch();
        this.setScreen(new BattleScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        AssetManager.INSTANCE.getAtlas().dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
