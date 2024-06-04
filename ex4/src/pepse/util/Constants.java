package pepse.util;

import danogl.util.Vector2;

import java.awt.*;

/**
 * Constants class containing various constants used in the game.
 */
public class Constants {

    /**
     * Tag for sky objects.
     */
    public static final String SKY_TAG = "sky";

    /**
     * Tag for night objects.
     */
    public static final String NIGHT_TAG = "night";

    /**
     * Tag for sun objects.
     */
    public static final String SUN_TAG = "sun";

    /**
     * Tag for block objects.
     */
    public static final String BLOCK_TAG = "block";

    /**
     * Tag for avatar objects.
     */
    public static final String AVATAR_TAG = "avatar";

    /**
     * Tag for fruit objects.
     * Format string with %d placeholder for numerical indexing.
     */
    public static final String FRUIT_TAG = "fruit%d";

    /**
     * Message for energy display.
     */
    public static final String ENERGY_MSG = "Energy: ";

    /**
     * Formatted message for energy display.
     */
    public static final String ENERGY_FORMATTED_MSG = "Energy: %.1f";

    /**
     * Type for idle images.
     */
    public static final String IDLE_IMAGE_TYPE = "idle_";

    /**
     * Type for jump images.
     */
    public static final String JUMP_IMAGE_TYPE = "jump_";

    /**
     * Type for run images.
     */
    public static final String RUN_IMAGE_TYPE = "run_";

    /**
     * Tag for sun halo objects.
     */
    public static final String SUN_HALO_TAG = "sunHalo";

    /**
     * Tag for trunk objects.
     */
    public static final String TRUNK_TAG = "TRUNK";

    /**
     * Path to the default image.
     */
    public static final String IMAGE_PATH = "assets/idle_0.png";

    /**
     * Base path for reading images.
     */
    public static final String IMAGE_PATH_FORMAT = "assets/%s%d.png";

    /**
     * Default color for sun halo.
     */
    public static final Color SUNHALO_DEFAULT_COLOR = new Color(255, 255, 0, 20);

    /**
     * Color for the trunk.
     */
    public static final Color COLOR_TRUNK = new Color(100, 50, 20);

    /**
     * Red color.
     */
    public static final Color RED_COLOR = new Color(200, 30, 30);

    /**
     * Color for leaves.
     */
    public static final Color LEAVES_COLOR = new Color(50, 200, 30);

    /**
     * Basic color for the sky.
     */
    public static final Color BASIC_SKY_COLOR = Color.decode("#80C6E5");

    /**
     * First brown color.
     */
    public static final Color FIRST_BROWN = new Color(212, 123, 74);

    /**
     * Trunk color.
     */
    public final static Color TRUNK_COLOR=new Color(  100,50,20);

    /**
     * Flag for transparency mode being on.
     */
    public static final boolean TRANSPARENCY_MODE_ON = true;

    /**
     * Midday constant.
     */
    public static final float MIDDAY = 0.0f;

    /**
     * Disappear factor constant.
     */
    public static final float DISAPPEAR_FACTOR = 0.000001f;
    /**
     * Appear factor constant.
     */
    public static final float APPEAR_FACTOR = 1f;

    /**
     * First random floating-point constant.
     */
    public static final float FLOAT_RANDOM1 = 0.1f;

    /**
     * Second random floating-point constant.
     */
    public static final float FLOAT_RANDOM2 = 0.5f;

    /**
     * Duration constant.
     */
    public static final float DURATION = 2.0f;

    /**
     * First random angle constant.
     */
    public static final float RANDOM_ANGLE1 = 20.0f;

    /**
     * Second random angle constant.
     */
    public static final float RANDOM_ANGLE2 = 25.0f;

    /**
     * Random angle constant.
     */
    public static final float ANGEL_RANDOM = 40.0f;

    /**
     * Waiting time constant.
     */
    public static final float WAITING_TIME = 30f;

    /**
     * Midnight constant.
     */
    public static final float MIDNIGHT = 0.5f;

    /**
     * Gravity constant.
     */
    public static final float GRAVITY = 600;

    /**
     * Sun radius constant.
     */
    public static final float RADIUS_SUN = 100;

    /**
     * Sun halo size adjustment constant.
     */
    public static final float SUN_HALO_SIZE_ADJUSTMENT = 1.5f;

    /**
     * Velocity in the X direction constant.
     */
    public static final float VELOCITY_X = 400;

    /**
     * Velocity in the Y direction constant.
     */
    public static final float VELOCITY_Y = -650;

    /**
     * Rest increment constant.
     */
    public static final float REST_INCREMENT = 1.0f;

    /**
     * Jump increment constant.
     */
    public static final float JUMP_INCREMENT = 10f;

    /**
     * Fruit increment constant.
     */
    public static final float FRUIT_INCREMENT = 10f;

    /**
     * Movement decrement constant.
     */
    public static final float MOVEMENT_DECREMENT = 0.5f;

    /**
     * Initial energy constant.
     */
    public static final float ENERGY_INIT = 100f;

    /**
     * Zero float constant.
     */
    public static final float ZERO_FLOAT = 0f;

    /**
     * Rotation constant.
     */
    public static final float RPTATE = 90f;

    /**
     * Time between audio clips constant for run animation.
     */
    public static final double RUN_TIME_BETWEEN_CLIPS = 0.1;

    /**
     * Time between audio clips constant for standing animation.
     */
    public static final double IDLE_TIME_BETWEEN_CLIPS = 0.1;

    /**
     * Time between audio clips constant for jumping animation.
     */
    public static final double JUMP_TIME_BETWEEN_CLIPS = 0.5;


    /**
     * Probability constant.
     */
    public static final double PROBALITY = 0.1;

    /**
     * Random number constant.
     */
    public static final double RANDOM_NUMBER = 0.5;

/**
 * Rotation cycle constant.
 */
public static final int ROTATE_CYCLE = 360;

    /**
     * Orbital radius constant.
     */
    public static final int ORBITAL_RADIUS = 125;

    /**
     * Ellipse axis A constant.
     */
    public static final int ORBIT_ELLIPSE_A_AXIS = 5;

    /**
     * Ellipse axis B constant.
     */
    public static final int ORBIT_ELLIPSE_B_AXIS = 3;

    /**
     * Noise factor constant.
     */
    public static final int NOISE_FACTOR = 7;

    /**
     * Seed constant for random number generation.
     */
    public static final int SEED = 11;

    /**
     * Number of run images constant.
     */
    public static final int RUN_IMAGES = 6;

    /**
     * Number of jump images constant.
     */
    public static final int JUMP_IMAGES = 4;

    /**
     * Number of idle images constant.
     */
    public static final int IDLE_IMAGES = 4;

    /**
     * Size of fruit constant.
     */
    public static final int FRUIT_SIZE = 20;

    /**
     * Size of leaf constant.
     */
    public static final int LEAF_SIZE = 20;

    /**
     * Size of block constant.
     */
    public static final int BLOCK_SIZE = 30;

    /**
     * Integer constant one.
     */
    public static final int ONE = 1;

    /**
     * Integer constant two.
     */
    public static final int TWO = 2;

    /**
     * Half cycle constant.
     */
    public static final int HALF_CYCLE = 15;

    /**
     * Initial value constant.
     */
    public static final int ZERO_INIT = 0;

    /**
     * Minimum height constant.
     */
    public static final int MIN_HEIGHT = 90;

    /**
     * Maximum height constant.
     */
    public static final int MAX_HEIGHT = 210;

    /**
     * Night cycle length constant.
     */
    public static final int NIGHT_CYCLE_LENGTH = 30;

    /**
     * Minimum value for tree dimension.
     */
    public static final int TREE_MIN = 100;

    /**
     * Maximum value for tree dimension.
     */
    public static final int TREE_MAX = 180;

    /**
     * Flag for unflippable behavior.
     */
    public static final boolean UNFLIPPABLE = false;

    /**
     * Flag for flippable behavior.
     */
    public static final boolean FLIPPABLE = true;

    /**
     * Default size for avatar.
     */
    public static final Vector2 AVATAR_DEFAULT_SIZE = Vector2.ONES.mult(50);

    /**
     * Location for energy display.
     */
    public static final Vector2 ENERGY_LOCATION = new Vector2(20, 20);

    /**
     * Dimensions for energy display.
     */
    public static final Vector2 ENERGY_DIMENSIONS = new Vector2(100, 20);

}
