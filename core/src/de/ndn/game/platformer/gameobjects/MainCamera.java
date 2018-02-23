package de.ndn.game.platformer.gameobjects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by nickn on 22.02.2018.
 */

public class MainCamera extends OrthographicCamera {

    private boolean isTracking;
    private Object trackedObject;
    private boolean xTracking;
    private boolean yTracking;

    private float deltaX;
    private float deltaY;
    private float deltaZoom;

    public MainCamera() {
        super();
        isTracking = false;
        xTracking = false;
        yTracking = false;
        resetDeltas();
    }

    public void startObjectTracking(Object object, boolean xTracking, boolean yTracking) {
        this.isTracking = true;
        this.trackedObject = object;
        this.xTracking = xTracking;
        this.yTracking = yTracking;
    }

    public void endObjectTracking() {
        isTracking = false;
        trackedObject = null;
        xTracking = false;
        yTracking = false;
    }

    public void moveUp() {
        deltaY = 1f;
    }
    public void moveDown() {
        deltaY = -1f;
    }
    public void moveRight() {
        deltaX = 1f;
    }
    public void moveLeft() {
        deltaX = -1f;
    }
    public void zoomIn() {
        deltaZoom = -0.01f;
    }
    public void zoomOut() {
        deltaZoom = 0.01f;
    }

    private void resetDeltas() {
        deltaX = 0f;
        deltaY = 0f;
        deltaZoom = 0f;
    }


    @Override
    public void update() {
        Vector3 tempPos = position;
        if(isTracking) {
            if(trackedObject instanceof Meeple) {
                Vector2 meeplePos = ((Meeple)trackedObject).getCenterPositionIP();
                if(xTracking) tempPos.x = meeplePos.x;
                if(yTracking) tempPos.y = meeplePos.y;
            }
        } else {
            tempPos.x += deltaX;
            tempPos.y += deltaY;
        }

        zoom += deltaZoom;
        position.set(tempPos);

        resetDeltas();

        super.update();
    }
}
