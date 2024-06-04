package bricker.main;

import danogl.util.Vector2;

/**
 * Contains constant values used throughout the application.
 */
public class CONSTANTS {



    // CAMERA CONSTANT :


    /**
     * Number of ball collisions before the camera zooms in.
     */
    public static final int NUM_BALL_COLLISION = 4;

    /**
     * Amount to zoom in the camera.
     */
    public static final float CAMERA_ZOOM_IN = 1.2f;

    /**
     * number zero
     */
    public static final int ZERO = 0;

    /**
     * maximum number until resting the camera to its original zoom
     */
    public static final int MAX_COLLIDE_TIMES_TO_RESET_CAMERA = 1;



    // BALL CONSTANTS :

    /**
     * Path to the image file of the ball.
     */
    public static final String BALL_IMAGE_PATH = "assets/ball.png";

    /**
     * Path to the sound file of the ball.
     */
    public static final String BALL_SOUND_PATH = "assets/blop_cut_silenced.wav";

    /**
     * Tag identifying the ball object.
     */
    public static final String BALL_TAG = "Ball";

    /**
     * Size of the mock ball.
     */
    public static final float MOCK_BALL_SIZE = 0.75F;

    /**
     * Speed of the ball.
     */
    public static final float BALL_SPEED = 450;

    /**
     * Default dimensions of the ball.
     */
    public static final Vector2 BALL_DEFAULT_DIMENSION = new Vector2(20 , 20);



    // Puck constants:

    /**
     * Tag identifying the puck object.
     */
    public static final String PUCK_TAG = "PUCK";

    /**
     * Path to the image file of the puck.
     */
    public static final String PUCK_IMAGE_PATH = "assets/mockBall.png";

    /**
     * Number of pucks to add.
     */
    public static final int NUM_PUCKS = 2;

    /**
     * Number of possible directions for the puck.
     */
    public static final int NUM_DIRECTIONS = 4;

    /**
     * pick right direction for puck
     */
    public static final int RIGHT_DIRECTION = 0;
    /**
     * pick left direction for puck
     */
    public static final int LEFT_DIRECTION = 1;
    /**
     * pick down direction for puck
     */
    public static final int DOWN_DIRECTION = 2;
    /**
     * pick up direction for puck
     */
    public static final int UP_DIRECTION = 3;






    // Game constants
    /**
     * Path to the background image used in the game.
     */
    public static final String BACKGROUND_IMAGE_PATH = "assets/DARK_BG2_small.jpeg";

    /**
     * Maximum number of arguments allowed.
     */
    public static final int MAX_ARGS_NUMBERS = 2;

    /**
     * Constant representing half, used for calculations.
     */
    public static final float HALF = 0.5F;

    /**
     * Default number of rows in the game.
     */
    public static final int DEFAULT_ROWS = 7;

    /**
     * Default number of columns in the game.
     */
    public static final int DEFAULT_COLUMNS = 8;

    /**
     * Initial value for initializing variables to zero.
     */
    public static final int ZERO_INITIALIZE = 0;

    /**
     * Index of the first argument in a list or array.
     */
    public static final int FIRST_ARG = 0;

    /**
     * Index of the second argument in a list or array.
     */
    public static final int SECOND_ARG = 1;

    /**
     * Flag indicating transparency mode is on.
     */
    public static final boolean TRANSPARENCY_MODE_ON = true;

    /**
     * Flag indicating transparency mode is off.
     */
    public static final boolean TRANSPARENCY_MODE_OF = false;

    /**
     * Initial X position for positioning objects.
     */
    public static final int INITIAL_X_POSITION = 5;

    /**
     * Initial Y position for positioning objects.
     */
    public static final int INITIAL_Y_POSITION = 5;

    /**
     * Name of the game.
     */
    public static final String GAME_NAME = "Bricker";

    /**
     * Default width resolution of the game window.
     */
    public static final int DEFAULT_WIDTH_RESOLUTION = 700;

    /**
     *  Default height resolution of the game window.
     */
    public static final int DEFAULT_HEIGHT_RESOLUTION = 500;





// Walls constants :
    /**
     * Represents the number of edges in the game.
     */
    public static final int NUMBER_OF_EDGES = 3;
    /**
     * Represents the dimensions of the horizontal edge.
     */
    public static final Vector2 HORIZONTAL_EDGE_DIMENSION = new Vector2(700, 5);
    /**
     * Represents the dimensions of the vertical edge.
     */
    public static final Vector2 VERTICAL_EDGE_DIMENSION = new Vector2(1, 500);
    /**
     * Represents the location of the left edge.
     */
    public static final Vector2 LEFT_EDGE_LOCATION = Vector2.ZERO;
    /**
     * Represents the location of the top edge.
     */
    public static final Vector2 UP_EDGE_LOCATION = Vector2.ZERO;
    /**
     * Represents the location of the right edge.
     */
    public static final Vector2 RIGHT_EDGE_LOCATION = new Vector2(700, 0);




    // Constants related to hearts in the game.

    /**
     * Represents the default dimensions of a heart.
     */
    public static final Vector2 HEART_DEFAULT_DIMENSIONS = new Vector2(35, 35);

    /**
     * Represents the speed of the heart movement.
     */
    public static final float HEART_SPEED = 400;

    /**
     * Represents the vertical location of hearts on the screen.
     */
    public static final int HEART_HEIGHT_LOCATION = 450;

    /**
     * Represents the maximum capacity of hearts.
     */
    public static final int HEART_MAX_CAPACITY = 4;

    /**
     * Represents the number of numeric lives displayed.
     */
    public static final int AMOUNT_NUMERIC_LIVES = 3;

    /**
     * Represents the index of the last numeric life.
     */
    public static final int LAST_NUMERIC_LIFE = 3;

    /**
     * Represents the index of the first numeric life.
     */
    public static final int FIRST_NUMERIC_LIFE = 0;

    /**
     * Represents the tag for falling hearts.
     */
    public static final String FALLING_HEART_TAG = "FALLING_HEART";

    /**
     * Represents the path to the heart image asset.
     */
    public static final String HEART_IMAGE_PATH = "assets/heart.png";

    /**
     * Represents the shift location for hearts.
     */
    public static final int HEART_SHIFT_LOCATION = 40;







    // Constants related to the paddle in the game.

    /**
     * Represents the default vertical location of the paddle.
     */
    public static final int PADDLE_DEFAULT_HEIGHT_LOCATION = 470;

    /**
     * Represents the default dimensions of the paddle.
     */
    public static final Vector2 PADDLE_DEFAULT_DIMENSIONS = new Vector2(200, 20);

    /**
     * Represents the default location of the limited paddle.
     */
    public static final Vector2 LIMITED_PADDLE_LOCATION =
            new Vector2((float) DEFAULT_WIDTH_RESOLUTION / 2, (float) DEFAULT_HEIGHT_RESOLUTION / 2);

    /**
     * Represents the counter for available limited paddles.
     */
    public static final int LIMITED_PADDLE_AVAILABLE_COUNTER = 0;

    /**
     * Represents the maximum number of collisions allowed for the limited paddle.
     */
    public static final int MAX_LIMITED_PADDLE_COLLISIONS_TIMES = 5;

    /**
     * Represents the tag for identifying the paddle.
     */
    public static final String PADDLE_TAG = "Paddle";

    /**
     * Represents the path to the paddle image asset.
     */
    public static final String PADDLE_IMAGE_PATH = "assets/paddle.png";

    /**
     * Represents the movement speed of the paddle.
     */
    public static final float PADDLE_MOVEMENT_SPEED = 600;

    /**
     * Represents the default size of the paddle.
     */
    public static final Vector2 PADDLE_DEFAULT_SIZE = new Vector2(200, 20) {};


    /**
     * Probability for paddle strategy
    */
    public static final int PADDLE_STRATEGY = 1;
    /**
     * Probability for puck strategy
     */
    public static final int PUCK_STRATEGY = 2;
    /**
     * Probability for camera strategy
     */
    public static final int CAMERA_STRATEGY = 3;
    /**
     * Probability for life-heart strategy
     */
    public static final int LIFE_STRATEGY = 4;
    /**
     * Probability for double strategy
     */
    public static final int DOUBLE_STRATEGY = 5;
    /**
     * number total strategies
     */
    public static final int TOTAL_STRATEGIES = 10;
    /**
     * number total double strategies
     */
    public static final int TOTAL_DOUBLE_STRATEGIES = 4;
    /**
     * adding one
     */
    public static final int ONE = 1;



    // Brick constants

    /**
     * Represents the path to the image file for the brick.
     */
    public static final String BRICK_IMAGE_PATH = "assets/brick.png";

    /**
     * Represents the tag identifying bricks in the game.
     */
    public static final String BRICK_TAG = "BRICK";

    /**
     * Represents the space between each brick in pixels.
     */
    public static final int SPACE_BETWEEN_BRICK = 5;

    /**
     * Represents the height of each brick in pixels.
     */
    public static final int BRICK_HEIGHT = 25;







    // user messages :

    /**
     * Message displayed when the player wins the game
     */
    public static final String WIN_CONDITION_MSG = "Congratulation, you won ! Do you wanna play again ?";

    /**
     * Message displayed when the player loses the game
     */
    public static final String LOSE_CONDITION_MSG = "Unfortunately, you lost !, Do you wanna play again ?";



}
