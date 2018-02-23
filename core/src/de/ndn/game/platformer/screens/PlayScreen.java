package de.ndn.game.platformer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import de.ndn.game.platformer.gameobjects.MainCamera;
import de.ndn.game.platformer.gameobjects.Meeple;
import de.ndn.game.platformer.gameobjects.PlatformLevel;
import de.ndn.game.platformer.utilities.CollisionDetection;
import de.ndn.game.platformer.utilities.DebugBodies;

import static de.ndn.game.platformer.constants.GameConstants.GAME_WIDTH;
import static de.ndn.game.platformer.constants.GameConstants.GAME_HEIGHT;
import static de.ndn.game.platformer.constants.GameConstants.PPM;
import static de.ndn.game.platformer.constants.GameConstants.TARGET_FPS;
import static de.ndn.game.platformer.constants.PhysicsConstants.X_GRAVITY;
import static de.ndn.game.platformer.constants.PhysicsConstants.Y_GRAVITY;

/**
 * Created by nickn on 20.02.2018.
 */

public class PlayScreen implements Screen {

    //Debugging
    private Box2DDebugRenderer b2dr;

    //Display
    private MainCamera camera;
    private Viewport viewport;

    //Game World
    private World world;
    private PlatformLevel level;
    private Meeple meeple;
    private ArrayList<Body> bodies;
    private SpriteBatch batch;

    private CollisionDetection cDet;
    private InputHandling inputHandling;


    public PlayScreen() {
        camera = new MainCamera();
        viewport = new FitViewport(GAME_WIDTH, GAME_HEIGHT, camera);
        camera.position.set(GAME_WIDTH/2, GAME_HEIGHT/2, 0);

        world = new World(new Vector2(X_GRAVITY, Y_GRAVITY), false);

        level = new PlatformLevel("levels/level0001/level0001.tmx", world, camera);
        meeple = new Meeple(32f, 64f, 100f, 500f, world);

        cDet = new CollisionDetection(meeple);
        world.setContactListener(cDet);

        inputHandling = new InputHandling(camera, meeple);

        camera.startObjectTracking(meeple, true, false);

        // Debugging
        //DebugBodies.createScreenBorder(world, 0f, 0f, 20f, false);
        //DebugBodies.createBox(world, 20f, 40f, 300f, 200f, false);
        b2dr = new Box2DDebugRenderer();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.step(1/((float)TARGET_FPS), 6, 2);

        /*
        try {
            Thread.sleep(50l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

        inputHandling.handleInput();
        meeple.update(delta);
        camera.update();
        level.update();

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        level.draw();

        // Debugging
        //b2dr.render(world, camera.combined.scl(PPM));
        //System.out.println(Gdx.graphics.getFramesPerSecond());
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
        level.dispose();
        batch.dispose();
    }
}
