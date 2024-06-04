package bricker.main;

import bricker.brick_strategies.CollisionStrategy;
import bricker.brick_strategies.StrategyFactory;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import bricker.gameobjects.*;
import java.awt.Color;




/**
 * The `BrickerGameManager` class manages the gameplay logic and objects for the Bricker game.
 * It extends the `GameManager` class and implements the game mechanics such as ball movement,
 * collision detection, paddle control, brick destruction and strategies .
 */
public class BrickerGameManager extends GameManager {
    private final Vector2 windowDimensions; // Dimensions of the game window.
    private final int rows; // Number of rows in the game board.
    private final int cols; // Number of columns in the game board.
    private WindowController windowController; // Controller for managing the game window.
    private Ball ballObject; // The ball object used in the game.
    private ImageReader imageReader; // Reader for loading images.
    private SoundReader soundReader; // Reader for loading sounds.
    private UserInputListener userInputListener; // Listener for user input.
    private StrategyFactory strategyFactory = null; // Factory for creating collision strategies.
    private Heart[] lifeCountersList; // Array containing life counter objects.
    private Counter liftBricks; // Counter for tracking the remaining bricks.
    Counter limitedPaddleCollisionsCounter; // Counter for tracking limited paddle collisions.
    Counter cameraResetCounter; // Counter for camera reset events.



    /**
     * Constructs a new BrickerGameManager object with specified window title, window dimensions,
     * number of rows, and number of columns.
     *
     * @param windowTitle      Title of the game window.
     * @param windowDimensions Dimensions of the game window.
     * @param rows             Number of rows in the game board.
     * @param cols             Number of columns in the game board.
     */
    public BrickerGameManager(String windowTitle, Vector2 windowDimensions, String rows, String cols) {
        super(windowTitle, windowDimensions);
        this.windowDimensions = windowDimensions;
        this.rows = Integer.parseInt(rows);
        this.cols = Integer.parseInt(cols);
    }

    /**
     * Default constructor that's  Constructs a new BrickerGameManager object with specified window title and
     * window
     * dimensions,
     * using default number of rows and columns.
     *
     * @param windowTitle      Title of the game window.
     * @param windowDimensions Dimensions of the game window.
     */
    public BrickerGameManager(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
        this.windowDimensions = windowDimensions;
        this.rows = CONSTANTS.DEFAULT_ROWS;
        this.cols = CONSTANTS.DEFAULT_COLUMNS;
    }




    /**
     * Updates the game objects for each second .
     * In this method I added the winCondition , and handled falling the ball.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        // Check if all bricks have been lifted, end game with win condition
        if (this.liftBricks.value() <= CONSTANTS.ZERO){
            endGameOpenWindow(true);
        }
        // Check if the ball falls below the window, handle lives and reset ball
        if (this.ballObject.getCenter().y() > this.windowDimensions.y()){
            if (!decreaseLives()) {
                endGameOpenWindow(false);
            } else {
                // reset ball location
                ballObject.setVelocity(Vector2.DOWN.multY(CONSTANTS.BALL_SPEED)); // Set ball speed
                ballObject.setCenter(windowController.getWindowDimensions().mult(CONSTANTS.HALF));
            }
        }
    }




    /**
     * Initializes the game with necessary resources and game objects.
     * In this method we initialize all game objects
     *
     * @param imageReader      The image reader for loading images.
     * @param soundReader      The sound reader for loading sounds.
     * @param inputListener    The input listener for user input.
     * @param windowController The window controller for managing the game window.
     */
    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader,
                               UserInputListener inputListener, WindowController windowController) {
        this.imageReader = imageReader;
        this.soundReader = soundReader;
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        this.windowController = windowController;
        this.userInputListener = inputListener;

        // initialize here to reset the arrays after resting the game .
        lifeCountersList =  new Heart[CONSTANTS.HEART_MAX_CAPACITY];
        liftBricks = new Counter(CONSTANTS.ZERO_INITIALIZE);
        limitedPaddleCollisionsCounter = new Counter(CONSTANTS.ZERO_INITIALIZE);
        cameraResetCounter = new Counter(CONSTANTS.ZERO_INITIALIZE);


        //add game background image
        createBackground();

        // add board edges
        createBoardEdges();


        // creating ball
        this.ballObject = (Ball) createBall(imageReader, windowController);

        // initialize game strategies
        initializeGameStrategies();

        // creating userPaddle
        createUserPaddle(windowController, inputListener);


        // Adding bricks
        createBoard();


        // Adding lifeCounter to the board
        initializeNumericLives();
    }

    /**
     * Initializes the numeric lives on the game board.
     * Numeric lives are represented by heart objects.
     */
    private void initializeNumericLives() {
        for (int i = CONSTANTS.ZERO_INITIALIZE; i < CONSTANTS.AMOUNT_NUMERIC_LIVES; ++i) {
            // Start from i=0, which is the first heart location, then i+=HEART_SHIFT_LOCATION, which is
            // the heart dimension plus a little space
            lifeCountersList[i] = new Heart(
                    new Vector2(CONSTANTS.HEART_SHIFT_LOCATION * i, CONSTANTS.HEART_HEIGHT_LOCATION),
                    CONSTANTS.HEART_DEFAULT_DIMENSIONS,
                    imageReader);
            gameObjects().addGameObject(lifeCountersList[i], Layer.UI);
        }
        // the fourth numeric life equals null until get numeric life from brick
        lifeCountersList[CONSTANTS.LAST_NUMERIC_LIFE] = null;
    }

    /**
     * Initializes the game strategies using the StrategyFactory.
     * The StrategyFactory is responsible for creating and managing brick strategies.
     */
    private void initializeGameStrategies() {
        this.strategyFactory = new StrategyFactory(gameObjects(), imageReader,
                soundReader, userInputListener, windowDimensions,
                this, lifeCountersList, limitedPaddleCollisionsCounter, cameraResetCounter);
    }


    /**
     * Creates the background GameObject and adds it to the gameObjects collection.
     */
    private void createBackground() {
        GameObject background = new GameObject(Vector2.ZERO, this.windowDimensions,
                imageReader.readImage(CONSTANTS.BACKGROUND_IMAGE_PATH, CONSTANTS.TRANSPARENCY_MODE_OF));
        this.gameObjects().addGameObject(background, Layer.BACKGROUND);
    }


    /**
     * Creates the board edges GameObjects and adds them to the gameObjects collection.
     */
    private void createBoardEdges(){
        Color myBorderColor = Color.BLUE;
        RectangleRenderable borderColor = new RectangleRenderable(myBorderColor);
        GameObject[] boardEdges = {
                // left wall
                new BoardEdge(CONSTANTS.LEFT_EDGE_LOCATION, CONSTANTS.VERTICAL_EDGE_DIMENSION, borderColor),
                // up wall
                new BoardEdge(CONSTANTS.UP_EDGE_LOCATION, CONSTANTS.HORIZONTAL_EDGE_DIMENSION, borderColor),
                // right wall
                new BoardEdge(CONSTANTS.RIGHT_EDGE_LOCATION, CONSTANTS.VERTICAL_EDGE_DIMENSION, borderColor),
        };

        // adding them to the board
        for (int i = CONSTANTS.ZERO_INITIALIZE; i < CONSTANTS.NUMBER_OF_EDGES; i++) {
            gameObjects().addGameObject(boardEdges[i]);
        }
    }

    /**
     * Creates a ball GameObject with the specified parameters and adds it to the gameObjects collection.
     *
     * @param imageReader        The ImageReader to read the ball image.
     * @param windowController   The WindowController to access window dimensions.
     * @return                   The created ball GameObject.
     */
    private GameObject createBall(ImageReader imageReader, WindowController windowController) {
        // create ball object
        Ball ball = new Ball(Vector2.ZERO, CONSTANTS.BALL_DEFAULT_DIMENSION,
                imageReader.readImage(CONSTANTS.BALL_IMAGE_PATH, CONSTANTS.TRANSPARENCY_MODE_ON),
                soundReader.readSound(CONSTANTS.BALL_SOUND_PATH), this,  this.cameraResetCounter);

        // make it fall down
        ball.setVelocity(Vector2.DOWN.multY(CONSTANTS.BALL_SPEED));
        ball.setCenter(windowController.getWindowDimensions().mult( CONSTANTS.HALF));

        ball.setTag(CONSTANTS.BALL_TAG);
        gameObjects().addGameObject(ball);

        return ball;
    }



    /**
     * Creates a user paddle GameObject with the specified parameters and adds it to the gameObjects
     * collection.
     *
     * @param windowController   The WindowController to access window dimensions.
     * @param inputListener      The UserInputListener to handle user input.
     */
    private void createUserPaddle(WindowController windowController, UserInputListener inputListener) {
        Paddle userPaddle = new Paddle(Vector2.ZERO, CONSTANTS.PADDLE_DEFAULT_SIZE , imageReader,
                inputListener, windowDimensions);
        userPaddle.setCenter(new Vector2(windowController.getWindowDimensions().x() / 2,
                CONSTANTS.PADDLE_DEFAULT_HEIGHT_LOCATION));
        userPaddle.setTag(CONSTANTS.PADDLE_TAG);
        gameObjects().addGameObject(userPaddle);
    }



    /**
     * Creates a brick GameObject with the specified parameters and adds it to the gameObjects collection.
     *
     * @param location         The position of the brick.
     * @param brickDimensions The dimensions of the brick.
     * @param liftBricks      The counter for lifting bricks.
     * @param strategies      The collision strategies for the brick.
     * @return                 The created brick GameObject.
     */
    private GameObject createBrick(Vector2 location, Vector2 brickDimensions,
                                   Counter liftBricks, CollisionStrategy[] strategies) {
        Brick brick = new Brick(location, brickDimensions, gameObjects(),
                imageReader.readImage(CONSTANTS.BRICK_IMAGE_PATH,
                        CONSTANTS.TRANSPARENCY_MODE_ON), strategies, liftBricks);
        brick.setTag(CONSTANTS.BRICK_TAG);
        return brick;
    }


    /**
     * Creates the game board by adding bricks to the gameObjects collection based on the
     * specified number of rows and columns.
     */
    private void createBoard() {
        // Calculate brick width based on window width and number of bricks per row
        int brickWidth = (int) (((windowController.getWindowDimensions().x()
                - (this.cols * CONSTANTS.SPACE_BETWEEN_BRICK)) / this.cols));
        int yPos = CONSTANTS.INITIAL_Y_POSITION; // first brick yLocation
        for (int layer = CONSTANTS.ZERO_INITIALIZE; layer < this.rows; layer++) {
            int xPos = CONSTANTS.INITIAL_X_POSITION; // first brick xLocation
            for (int brick = CONSTANTS.ZERO_INITIALIZE; brick < this.cols; brick++) {
                Vector2 brickPosition = new Vector2(xPos, yPos);
                Vector2 brickDimensions = new Vector2(brickWidth, CONSTANTS.BRICK_HEIGHT);
                Brick brickObject = (Brick) createBrick(brickPosition, brickDimensions,
                        liftBricks, strategyFactory.getRandomStrategies());
                liftBricks.increment();
                gameObjects().addGameObject(brickObject);
                xPos += (brickWidth + CONSTANTS.SPACE_BETWEEN_BRICK); // Increment by brick width + space
            }
            // Increment by brick HEIGHT + space
            yPos += (CONSTANTS.BRICK_HEIGHT + CONSTANTS.SPACE_BETWEEN_BRICK);
        }
    }





    /**
     * Ends the game and opens a window with a message indicating win or lose condition.
     * If it's a win condition, prompts the user to reset the game; otherwise, closes the window.
     *
     * @param isWinCondition True if the game ended with a win condition, false otherwise.
     */
    private void endGameOpenWindow(boolean isWinCondition) {
        if (this.windowController.openYesNoDialog(isWinCondition ? CONSTANTS.WIN_CONDITION_MSG :
                CONSTANTS.LOSE_CONDITION_MSG)) {
            // reset the game + resetting all objects
            this.windowController.resetGame();
        } else {
            // close game window
            this.windowController.closeWindow();
        }
    }

    /**
     * Decreases the player's lives when the ball falls out.
     *
     * @return true if lives were successfully decreased, false otherwise.
     */
    private boolean decreaseLives() {
        int i = lifeCountersList.length - CONSTANTS.ONE; // Start from the last index

        while (i >= CONSTANTS.FIRST_NUMERIC_LIFE && lifeCountersList[i] == null) {
            i--; // Move towards the beginning until finding a non-null element
        }

        // Remove the first non-null element found
        if (i > CONSTANTS.FIRST_NUMERIC_LIFE) {
            gameObjects().removeGameObject(lifeCountersList[i], Layer.UI);
            lifeCountersList[i] = null;
            return true;
        } else {
            return false;
        }
    }


    /**
     * Entry point of the Bricker game.
     * Initializes and runs the game based on command line arguments, if provided.
     * If no arguments are provided, the game runs with default settings.
     * @param args Command line arguments (optional) - window title, rows, and columns.
     */
    public static void main(String[] args) {
        BrickerGameManager ballGame;

        // Check if command line arguments are provided
        if (args.length == CONSTANTS.MAX_ARGS_NUMBERS){
            ballGame = new BrickerGameManager(CONSTANTS.GAME_NAME,
                    new Vector2(CONSTANTS.DEFAULT_WIDTH_RESOLUTION,
                            CONSTANTS.DEFAULT_HEIGHT_RESOLUTION), args[CONSTANTS.SECOND_ARG] ,
                    args[CONSTANTS.FIRST_ARG]);
        }
        else {
            // Run the game with default settings
            ballGame = new BrickerGameManager(CONSTANTS.GAME_NAME,
                    new Vector2(CONSTANTS.DEFAULT_WIDTH_RESOLUTION,
                            CONSTANTS.DEFAULT_HEIGHT_RESOLUTION));
        }
        ballGame.run(); // Start the game
    }


}

