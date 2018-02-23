package de.ndn.game.platformer.constants;

/**
 * Created by nickn on 20.02.2018.
 */

public class PhysicsConstants {
    public static final float Y_GRAVITY = -35f;
    public static final float X_GRAVITY = 0f;

    public enum GameObjectType {
        GROUND,
        WALLS,
        JUMPMARK,

        MEEPLEFEETS,
        MEEPLEBODY,
    }

    //Ground
    public static final float   GROUND_DENSITY                  = 1f;
    public static final float   GROUND_FRICTION                 = 1f;
    public static final float   GROUND_RESTITUTION              = 0f;
    public static final short   GROUND_FILTER_CATEGORY_BIT      = 1;
    public static final short   GROUND_FILTER_MASK_BIT          = 2;
    public static final short   GROUND_FILTER_GROUP_INDEX       = 0;
    public static final boolean GROUND_IS_SENSOR                = false;

    //Jump Mark
    public static final float   JUMPMARK_DENSITY                = 0f;
    public static final float   JUMPMARK_FRICTION               = 0f;
    public static final float   JUMPMARK_RESTITUTION            = 0f;
    public static final short   JUMPMARK_FILTER_CATEGORY_BIT    = 1;
    public static final short   JUMPMARK_FILTER_MASK_BIT        = 2;
    public static final short   JUMPMARK_FILTER_GROUP_INDEX     = 1;
    public static final boolean JUMPMARK_IS_SENSOR              = true;

    //Meeple
    public static final float   MEEPLE_GROUND_GRAVITY_FACTOR    = 3f;
    //Meeple - Feets
    public static final float   MEEPLEFEETS_DENSITY                = 1f;
    public static final float   MEEPLEFEETS_FRICTION               = 1f;
    public static final float   MEEPLEFEETS_RESTITUTION            = 0f;
    public static final short   MEEPLEFEETS_FILTER_CATEGORY_BIT    = 2;
    public static final short   MEEPLEFEETS_FILTER_MASK_BIT        = 1;
    public static final short   MEEPLEFEETS_FILTER_GROUP_INDEX     = 2;
    public static final boolean MEEPLEFEETS_IS_SENSOR              = false;
    //Meeple - Body
    public static final float   MEEPLEBODY_DENSITY                = 0f;
    public static final float   MEEPLEBODY_FRICTION               = 0f;
    public static final float   MEEPLEBODY_RESTITUTION            = 0f;
    public static final short   MEEPLEBODY_FILTER_CATEGORY_BIT    = 2;
    public static final short   MEEPLEBODY_FILTER_MASK_BIT        = 1;
    public static final short   MEEPLEBODY_FILTER_GROUP_INDEX     = 2;
    public static final boolean MEEPLEBODY_IS_SENSOR              = false;


}
