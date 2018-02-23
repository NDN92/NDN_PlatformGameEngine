package de.ndn.game.platformer.utilities;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import de.ndn.game.platformer.gameobjects.Meeple;

import static de.ndn.game.platformer.constants.PhysicsConstants.GameObjectType.GROUND;
import static de.ndn.game.platformer.constants.PhysicsConstants.GameObjectType.JUMPMARK;
import static de.ndn.game.platformer.constants.PhysicsConstants.GameObjectType.MEEPLEFEETS;

/**
 * Created by nickn on 20.02.2018.
 */

public class CollisionDetection implements ContactListener {

    private Meeple meeple;

    private boolean noFixing;

    public CollisionDetection(Meeple meeple) {
        this.meeple = meeple;
        noFixing = false;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        if(!noFixing) {
            if( (a.getUserData().equals(GROUND) && b.getUserData().equals(MEEPLEFEETS))
                    || (b.getUserData().equals(GROUND) && a.getUserData().equals(MEEPLEFEETS))) {
                meeple.fixOnGround();
            }
        }

        if( (a.getUserData().equals(JUMPMARK) && b.getUserData().equals(MEEPLEFEETS))
                || (b.getUserData().equals(JUMPMARK) && a.getUserData().equals(MEEPLEFEETS))) {
            //meeple.defixOnGround();
            noFixing = true;
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        if( (a.getUserData().equals(GROUND) && b.getUserData().equals(MEEPLEFEETS))
                || (b.getUserData().equals(GROUND) && a.getUserData().equals(MEEPLEFEETS))) {
            //meeple.defixPlayOnGround();
        }

        if( (a.getUserData().equals(JUMPMARK) && b.getUserData().equals(MEEPLEFEETS))
                || (b.getUserData().equals(JUMPMARK) && a.getUserData().equals(MEEPLEFEETS))) {
            noFixing = false;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


}
