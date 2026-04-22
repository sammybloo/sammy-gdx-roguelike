package io.bloogames.deckbuilder;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.screen.BattleScreen;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends Game {

    private SpriteBatch batch;
    private Viewport viewport;


    public Viewport getViewport() {
        return viewport;
    }

    @Override
    public void create() {
        this.viewport = new ExtendViewport(1920, 1080);

        this.setScreen(new BattleScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        AssetManager.INSTANCE.dispose();
        FontManager.INSTANCE.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        if (getScreen() != null) {
            getScreen().resize(width, height);
        }
    }
}
