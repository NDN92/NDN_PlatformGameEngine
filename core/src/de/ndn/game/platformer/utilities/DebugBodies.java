package de.ndn.game.platformer.utilities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import de.ndn.game.platformer.constants.GameConstants;
import de.ndn.game.platformer.constants.PhysicsConstants;

/**
 * Created by nickn on 22.02.2018.
 */

public class DebugBodies {

    public static void createScreenBorder(World world, float xPosIP, float yPosIP, float innerBorderIP, boolean isCollison) {
        Body body;
        BodyDef bDef = new BodyDef();
        FixtureDef fDef = new FixtureDef();

        //Ground
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.position.set(xPosIP/ GameConstants.PPM, yPosIP/GameConstants.PPM);

        ChainShape chShape = new ChainShape();
        chShape.createChain( new float[] {
                (0 + innerBorderIP) / GameConstants.PPM,
                (0 + innerBorderIP) / GameConstants.PPM,

                (0 + innerBorderIP) / GameConstants.PPM,
                (0 + GameConstants.GAME_HEIGHT - innerBorderIP) / GameConstants.PPM,

                (0 + GameConstants.GAME_WIDTH - innerBorderIP) / GameConstants.PPM,
                (0 + GameConstants.GAME_HEIGHT - innerBorderIP) / GameConstants.PPM,

                (0 + GameConstants.GAME_WIDTH - innerBorderIP) / GameConstants.PPM,
                (0 + innerBorderIP) / GameConstants.PPM,

                (0 + innerBorderIP) / GameConstants.PPM,
                (0 + innerBorderIP) / GameConstants.PPM
        });

        fDef.shape = chShape;
        fDef.density = PhysicsConstants.GROUND_DENSITY;
        fDef.friction = PhysicsConstants.GROUND_FRICTION;
        fDef.restitution = PhysicsConstants.GROUND_RESTITUTION;
        fDef.filter.categoryBits = PhysicsConstants.GROUND_FILTER_CATEGORY_BIT;
        fDef.filter.maskBits = PhysicsConstants.GROUND_FILTER_MASK_BIT;
        fDef.filter.groupIndex = PhysicsConstants.GROUND_FILTER_GROUP_INDEX;
        fDef.isSensor = !isCollison;

        body = world.createBody(bDef);
        body.createFixture(fDef).setUserData(PhysicsConstants.GameObjectType.GROUND);
        chShape.dispose();
    }

    public static void createBox(World world, float widthIP, float heightIP, float xPosIP, float yPosIP, boolean isCollison) {
        Body body;
        BodyDef bDef = new BodyDef();
        FixtureDef fDef = new FixtureDef();

        //Ground
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.position.set(xPosIP / GameConstants.PPM, yPosIP / GameConstants.PPM);

        PolygonShape pShape = new PolygonShape();
        pShape.setAsBox(widthIP / GameConstants.PPM, heightIP / GameConstants.PPM);

        fDef.shape = pShape;
        fDef.density = PhysicsConstants.GROUND_DENSITY;
        fDef.friction = PhysicsConstants.GROUND_FRICTION;
        fDef.restitution = PhysicsConstants.GROUND_RESTITUTION;
        fDef.filter.categoryBits = PhysicsConstants.GROUND_FILTER_CATEGORY_BIT;
        fDef.filter.maskBits = PhysicsConstants.GROUND_FILTER_MASK_BIT;
        fDef.filter.groupIndex = PhysicsConstants.GROUND_FILTER_GROUP_INDEX;
        fDef.isSensor = !isCollison;

        body = world.createBody(bDef);
        body.createFixture(fDef).setUserData(PhysicsConstants.GameObjectType.GROUND);
        pShape.dispose();
    }

}
