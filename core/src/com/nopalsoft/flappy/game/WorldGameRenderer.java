package com.nopalsoft.flappy.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.nopalsoft.flappy.Assets;
import com.nopalsoft.flappy.game_objects.Bird;
import com.nopalsoft.flappy.game_objects.Pipe;
import com.nopalsoft.flappy.screens.Screens;

public class WorldGameRenderer {

    final float WIDTH = Screens.WORLD_WIDTH;
    final float HEIGHT = Screens.WORLD_HEIGHT;

    SpriteBatch spriteBatch;
    WorldGame worldGame;
    OrthographicCamera camera;

    Box2DDebugRenderer debugRenderBox;

    public WorldGameRenderer(SpriteBatch batcher, WorldGame worldGame) {

        this.camera = new OrthographicCamera(WIDTH, HEIGHT);
        this.camera.position.set(WIDTH / 2f, HEIGHT / 2f, 0);
        this.spriteBatch = batcher;
        this.worldGame = worldGame;
        this.debugRenderBox = new Box2DDebugRenderer();
    }

    public void render(float delta) {

        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();
        spriteBatch.disableBlending();
        drawBackground(delta);
        spriteBatch.enableBlending();
        drawPipe(delta);
        drawBird(delta);

        spriteBatch.end();

    }

    private void drawBackground(float delta) {
        spriteBatch.draw(Assets.background, 0, 0, WIDTH, HEIGHT);
    }

    private void drawPipe(float delta) {
        for (Pipe obj : worldGame.pipes) {
            if (obj.type == Pipe.TYPE_DOWN)
                spriteBatch.draw(Assets.downPipe, obj.position.x - .5f,
                        obj.position.y - 2f, 1f, 4);
            else
                spriteBatch.draw(Assets.upPipe, obj.position.x - .5f,
                        obj.position.y - 2f, 1f, 4);
        }
    }

    private void drawBird(float delta) {
        Bird obj = worldGame.bird;
        TextureRegion keyFrame;

        if (obj.state == Bird.STATE_NORMAL) {
            keyFrame = Assets.bird.getKeyFrame(obj.stateTime, true);
        } else {
            keyFrame = Assets.bird.getKeyFrame(obj.stateTime, false);
        }
        spriteBatch.draw(keyFrame, obj.position.x - .3f, obj.position.y - .25f, .6f, .5f);
    }

}
