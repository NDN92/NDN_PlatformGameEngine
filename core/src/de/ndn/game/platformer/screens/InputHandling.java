package de.ndn.game.platformer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import de.ndn.game.platformer.gameobjects.MainCamera;
import de.ndn.game.platformer.gameobjects.Meeple;

/**
 * Created by nickn on 22.02.2018.
 */

public class InputHandling extends InputListener {

    private MainCamera camera;
    private Meeple meeple;

    public InputHandling(MainCamera camera, Meeple meeple) {
        super();
        this.camera = camera;
        this.meeple = meeple;
    }

    public void handleInput() {

        if(!(Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                && Gdx.input.isKeyPressed(Input.Keys.LEFT))) {
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) meeple.walkRight();
            else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))  meeple.walkLeft();
            else meeple.standStill();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            meeple.jump();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            meeple.duck();
        }




        //Debugging
        if(!(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_8)
                && Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2))) {
            if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_8)) camera.moveUp();
            if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)) camera.moveDown();
        }
        if(!(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6)
                && Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4))) {
            if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6)) camera.moveRight();
            if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)) camera.moveLeft();
        }
        if(!(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_9)
                && Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3))) {
            if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_9))  camera.zoomIn();
            if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3)) camera.zoomOut();
        }
    }

}
