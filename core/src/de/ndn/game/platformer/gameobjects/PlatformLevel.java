package de.ndn.game.platformer.gameobjects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import de.ndn.game.platformer.constants.GameConstants;
import de.ndn.game.platformer.constants.PhysicsConstants;

/**
 * Created by nickn on 20.02.2018.
 */

public class PlatformLevel {

    private World world;
    private OrthographicCamera camera;

    private TiledMap map;
    private TiledMapRenderer tmr;

    public PlatformLevel(String path, World world, OrthographicCamera camera) {
        this.world = world;
        this.camera = camera;

        map = new TmxMapLoader().load(path);
        tmr = new OrthogonalTiledMapRenderer(map);

        parseTiledMapObjects();
    }

    private void parseTiledMapObjects() {
        Body body;
        BodyDef bDef = new BodyDef();
        FixtureDef fDef = new FixtureDef();
        MapLayer mapLayer;
        MapObjects mapObjects;

        mapLayer = map.getLayers().get("GROUNDS");
        if(mapLayer != null) {
            mapObjects = mapLayer.getObjects();
            for(MapObject mapObject : mapObjects) {
                Shape shape;

                if(mapObject instanceof PolylineMapObject) {
                    shape = createChainShape((PolylineMapObject) mapObject);
                } else {
                    continue;
                }

                bDef.type = BodyDef.BodyType.StaticBody;
                bDef.position.set(0f/GameConstants.PPM, 0f/GameConstants.PPM);

                fDef.shape = shape;
                fDef.density = PhysicsConstants.GROUND_DENSITY;
                fDef.friction = PhysicsConstants.GROUND_FRICTION;
                fDef.restitution = PhysicsConstants.GROUND_RESTITUTION;
                fDef.filter.categoryBits = PhysicsConstants.GROUND_FILTER_CATEGORY_BIT;
                fDef.filter.maskBits = PhysicsConstants.GROUND_FILTER_MASK_BIT;
                fDef.filter.groupIndex = PhysicsConstants.GROUND_FILTER_GROUP_INDEX;
                fDef.isSensor = PhysicsConstants.GROUND_IS_SENSOR;

                body = world.createBody(bDef);
                body.createFixture(fDef).setUserData(PhysicsConstants.GameObjectType.GROUND);
                shape.dispose();
            }
        }

        mapLayer = map.getLayers().get("WALLS");
        if(mapLayer != null) {
            mapObjects = mapLayer.getObjects();
            for(MapObject mapObject : mapObjects) {
                Shape shape;

                if(mapObject instanceof PolylineMapObject) {
                    shape = createChainShape((PolylineMapObject) mapObject);
                } else {
                    continue;
                }

                bDef.type = BodyDef.BodyType.StaticBody;
                bDef.position.set(0f/GameConstants.PPM, 0f/GameConstants.PPM);

                fDef.shape = shape;
                fDef.density = PhysicsConstants.GROUND_DENSITY;
                fDef.friction = PhysicsConstants.GROUND_FRICTION;
                fDef.restitution = PhysicsConstants.GROUND_RESTITUTION;
                fDef.filter.categoryBits = PhysicsConstants.GROUND_FILTER_CATEGORY_BIT;
                fDef.filter.maskBits = PhysicsConstants.GROUND_FILTER_MASK_BIT;
                fDef.filter.groupIndex = PhysicsConstants.GROUND_FILTER_GROUP_INDEX;
                fDef.isSensor = PhysicsConstants.GROUND_IS_SENSOR;

                body = world.createBody(bDef);
                body.createFixture(fDef).setUserData(PhysicsConstants.GameObjectType.WALLS);
                shape.dispose();
            }
        }

        mapLayer = map.getLayers().get("JUMPMARKS");
        if(mapLayer != null) {
            mapObjects = mapLayer.getObjects();
            for(MapObject mapObject : mapObjects) {
                Shape shape;

                if(mapObject instanceof PolylineMapObject) {
                    shape = createChainShape((PolylineMapObject) mapObject);
                } else {
                    continue;
                }

                bDef.type = BodyDef.BodyType.StaticBody;
                bDef.position.set(0f/GameConstants.PPM, 0f/GameConstants.PPM);

                fDef.shape = shape;
                fDef.density = PhysicsConstants.JUMPMARK_DENSITY;
                fDef.friction = PhysicsConstants.JUMPMARK_FRICTION;
                fDef.restitution = PhysicsConstants.JUMPMARK_RESTITUTION;
                fDef.filter.categoryBits = PhysicsConstants.JUMPMARK_FILTER_CATEGORY_BIT;
                fDef.filter.maskBits = PhysicsConstants.JUMPMARK_FILTER_MASK_BIT;
                fDef.filter.groupIndex = PhysicsConstants.JUMPMARK_FILTER_GROUP_INDEX;
                fDef.isSensor = PhysicsConstants.JUMPMARK_IS_SENSOR;

                body = world.createBody(bDef);
                body.createFixture(fDef).setUserData(PhysicsConstants.GameObjectType.JUMPMARK);
                shape.dispose();
            }
        }

    }

    private ChainShape createChainShape(PolylineMapObject polyline) {
        float[] vertices = polyline.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];
        for(int i = 0; i < worldVertices.length; i++) {
            worldVertices[i] = new Vector2(vertices[i*2] / GameConstants.PPM,
                    vertices[i*2 + 1] / GameConstants.PPM);
        }
        ChainShape cs = new ChainShape();
        cs.createChain(worldVertices);
        return cs;
    }

    public void update() {
        tmr.setView(camera);
    }

    public void draw() {
        tmr.render();
    }

    public void dispose() {
        map.dispose();
    }

}
