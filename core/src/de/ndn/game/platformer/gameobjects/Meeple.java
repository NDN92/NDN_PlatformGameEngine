package de.ndn.game.platformer.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

import de.ndn.game.platformer.constants.GameConstants;
import de.ndn.game.platformer.constants.PhysicsConstants;

/**
 * Created by nickn on 21.02.2018.
 */

public class Meeple {

    private World world;

    private Body meepleFeets, meepleBody;
    private Joint feetsBodyJoint;

    private float width, height;

    private float horizontalForce;
    private float verticalForce;
    private boolean inAir;
    private boolean inHorizontalMotion;

    public Meeple(float widthIP, float heightIP, float xPosIP, float yPosIP, World world) {
        this.world = world;
        this.width = widthIP / GameConstants.PPM;
        this.height = heightIP / GameConstants.PPM;


        float feetsRadius = (width / 2f) /*- (2f / GameConstants.PPM)*/;
        float feetsPosX   = xPosIP / GameConstants.PPM;
        float feetsPosY   = (yPosIP / GameConstants.PPM) + feetsRadius;
        float bodySizeX   = width / 2f;
        float bodySizeY   = (height - feetsRadius) / 2;
        float bodyPosX    = feetsPosX;
        float bodyPosY    = feetsPosY + bodySizeY;


        BodyDef bDef = new BodyDef();
        FixtureDef fDef = new FixtureDef();

        //Meeple - Feets
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(feetsPosX, feetsPosY);

        CircleShape ciShape = new CircleShape();
        ciShape.setRadius(feetsRadius);

        fDef.shape = ciShape;
        fDef.density = PhysicsConstants.MEEPLEFEETS_DENSITY;
        fDef.friction = PhysicsConstants.MEEPLEFEETS_FRICTION;
        fDef.restitution = PhysicsConstants.MEEPLEFEETS_RESTITUTION;
        fDef.filter.categoryBits = PhysicsConstants.MEEPLEFEETS_FILTER_CATEGORY_BIT;
        fDef.filter.maskBits = PhysicsConstants.MEEPLEFEETS_FILTER_MASK_BIT;
        fDef.filter.groupIndex = PhysicsConstants.MEEPLEFEETS_FILTER_GROUP_INDEX;
        fDef.isSensor = PhysicsConstants.MEEPLEFEETS_IS_SENSOR;

        meepleFeets = world.createBody(bDef);
        meepleFeets.createFixture(fDef).setUserData(PhysicsConstants.GameObjectType.MEEPLEFEETS);
        meepleFeets.setGravityScale(1f);
        ciShape.dispose();


        //Meeple - Body
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(bodyPosX, bodyPosY);
        bDef.fixedRotation = true;

        PolygonShape pShape = new PolygonShape();
        pShape.setAsBox(bodySizeX, bodySizeY);

        fDef.shape = pShape;
        fDef.density = PhysicsConstants.MEEPLEBODY_DENSITY;
        fDef.friction = PhysicsConstants.MEEPLEBODY_FRICTION;
        fDef.restitution = PhysicsConstants.MEEPLEBODY_RESTITUTION;
        fDef.filter.categoryBits = PhysicsConstants.MEEPLEBODY_FILTER_CATEGORY_BIT;
        fDef.filter.maskBits = PhysicsConstants.MEEPLEBODY_FILTER_MASK_BIT;
        fDef.filter.groupIndex = PhysicsConstants.MEEPLEBODY_FILTER_GROUP_INDEX;
        fDef.isSensor = PhysicsConstants.MEEPLEBODY_IS_SENSOR;

        meepleBody = world.createBody(bDef);
        meepleBody.createFixture(fDef).setUserData(PhysicsConstants.GameObjectType.MEEPLEBODY);
        pShape.dispose();


        //Meeple Connection
        RevoluteJointDef jDef = new RevoluteJointDef();
        jDef.bodyA = meepleBody;
        jDef.bodyB = meepleFeets;
        jDef.localAnchorA.set(0f, bodySizeY * (-1));
        jDef.localAnchorB.set(0f, 0f);

        feetsBodyJoint = world.createJoint(jDef);


        inAir = true;
        inHorizontalMotion = false;
    }

    public void walkRight() {
        horizontalForce = 6f;
        meepleFeets.setFixedRotation(false);
        inHorizontalMotion = true;
    }
    public void walkLeft() {
        horizontalForce = -6f;
        meepleFeets.setFixedRotation(false);
        inHorizontalMotion = true;
    }
    public void jump() {
        if(!inAir) {
            defixOnGround();
            verticalForce = 2000f;
            meepleFeets.setLinearVelocity(0f, 0f);
            meepleBody.setLinearVelocity(0f, 0f);
        }
    }
    public void duck() {
        verticalForce = -50f;
    }
    public void standStill() {
        meepleFeets.setFixedRotation(true);
    }
    public void fixOnGround() {
        if(inAir) {
            meepleFeets.setGravityScale(PhysicsConstants.MEEPLE_GROUND_GRAVITY_FACTOR);
            inAir = false;
        }
    }
    public void defixOnGround() {
        if(!inAir) {
            meepleFeets.setGravityScale(1f);
            inAir = true;
        }
    }
    private void resetForces() {
        horizontalForce = 0f;
        verticalForce = 0f;
        inHorizontalMotion = false;
    }

    public void update(float delta) {
        //System.out.println("X: " + meepleFeets.getLinearVelocity().x + " | Y: " + meepleFeets.getLinearVelocity().y);
        //System.out.println("X: " + meepleBody.getLinearVelocity().x + " | Y: " + meepleBody.getLinearVelocity().y);
        float fpsFactor = delta / (1/(float)GameConstants.TARGET_FPS);

        meepleFeets.applyForceToCenter(0, verticalForce, false);
        meepleFeets.setLinearVelocity(horizontalForce * fpsFactor, meepleFeets.getLinearVelocity().y);

        resetForces();
    }


    public Vector2 getCenterPositionIP() {
        return new Vector2(meepleFeets.getPosition().x * GameConstants.PPM, (meepleFeets.getPosition().y - meepleFeets.getFixtureList().get(0).getShape().getRadius() + (height / 2f)) * GameConstants.PPM);
    }


}
