package bricker.brick_strategies;

import danogl.collisions.GameObjectCollection;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.util.Counter;
import danogl.util.Vector2;
import bricker.gameobjects.Heart;
import bricker.main.CONSTANTS;
import danogl.gui.ImageReader;
import bricker.main.BrickerGameManager;

import java.util.Random;

/**
 * The StrategyFactory class is responsible for creating various collision strategies
 * used in the game.
 */
public class StrategyFactory {
    private final GameObjectCollection gameObjectCollection;
    private final ImageReader imageReader;
    private final SoundReader soundReader;
    private final UserInputListener userInputListener;
    private final Vector2 windowDimensions;
    private final BrickerGameManager game;
    private final Heart[] lifeCountersList;
    private final Counter limitedPaddleCollisionsCounter;
    private final Counter cameraResetCounter;

    /**
     * Constructs a new StrategyFactory with the given parameters.
     *
     * @param gameObjectCollection           The collection of game objects.
     * @param imageReader                    The image reader to load images.
     * @param soundReader                    The sound reader to load sounds.
     * @param userInputListener              The user input listener.
     * @param windowDimensions               The dimensions of the game window.
     * @param game                           The bricker.main game instance.
     * @param lifeCountersList               The array of life counters.
     * @param limitedPaddleCollisionsCounter The counter for limited paddle collisions.
     * @param cameraResetCounter             The counter for camera reset.
     */
    public StrategyFactory(GameObjectCollection gameObjectCollection, ImageReader imageReader,
                           SoundReader soundReader, UserInputListener userInputListener,
                           Vector2 windowDimensions, BrickerGameManager game, Heart[] lifeCountersList,
                           Counter limitedPaddleCollisionsCounter, Counter cameraResetCounter) {
        this.gameObjectCollection = gameObjectCollection;
        this.imageReader = imageReader;
        this.soundReader = soundReader;
        this.userInputListener = userInputListener;
        this.windowDimensions = windowDimensions;
        this.game = game;
        this.lifeCountersList = lifeCountersList;
        this.limitedPaddleCollisionsCounter = limitedPaddleCollisionsCounter;
        this.cameraResetCounter = cameraResetCounter;
    }

    /**
     * Creates a CollisionStrategy for adding a paddle to the game.
     *
     * @return A CollisionStrategy for adding a paddle.
     */
    public CollisionStrategy paddle(){
        return new AddPaddleStrategy(this.gameObjectCollection, this.imageReader, this.userInputListener,
                this.windowDimensions, this.limitedPaddleCollisionsCounter);
    }

    /**
     * Creates a CollisionStrategy for adding a puck to the game.
     *
     * @return A CollisionStrategy for adding a puck.
     */
    public CollisionStrategy puck(){
        return new AddPuckStrategy(this.gameObjectCollection, this.imageReader, this.soundReader, this.game,
                this.cameraResetCounter);
    }

    /**
     * Creates a CollisionStrategy for adding a life to the game.
     *
     * @return A CollisionStrategy for adding a life.
     */
    public CollisionStrategy life(){
        return new AddLifeStrategy(this.gameObjectCollection, this.imageReader,
                this.lifeCountersList);
    }

    /**
     * Creates a CollisionStrategy for adding a camera to the game.
     *
     * @return A CollisionStrategy for adding a camera.
     */
    public CollisionStrategy camera(){
        return new AddCameraStrategy(this.gameObjectCollection, this.game, this.windowDimensions,
                this.cameraResetCounter);
    }

    /**
     * Creates a CollisionStrategy for removing the brick from the game.
     *
     * @return A CollisionStrategy for removing the brick.
     */
    public CollisionStrategy remove(){
        return new BasicCollisionStrategy(this.gameObjectCollection);
    }


    /**
     *  Randomly pick strategy of the 5 strategies , it's get a random number between 1-10 then check
     *  for the cases from 1-5 which give 1/10 probability for each strategy and the rest is for the remove
     *  which will get 5/10 .
     *
     * @param random A reference for Random to use the nextInt method for the double strategy .
     * @param randomNumber A random number between 1-10 to choose strategy for the brick .
     * @return A CollisionStrategy array with the random picked strategy .
     */
    private CollisionStrategy[] randomPickStrategy(Random random, int randomNumber) {
        switch (randomNumber){
            case CONSTANTS.PADDLE_STRATEGY:
                return new CollisionStrategy[]{paddle()};
            case CONSTANTS.PUCK_STRATEGY:
                return new CollisionStrategy[]{puck()};
            case CONSTANTS.CAMERA_STRATEGY:
                return new CollisionStrategy[]{camera()};
            case CONSTANTS.LIFE_STRATEGY:
                return new CollisionStrategy[]{life()};
            case CONSTANTS.DOUBLE_STRATEGY:
                int randomDoubleNumber = random.nextInt(CONSTANTS.DOUBLE_STRATEGY) + CONSTANTS.ONE;
                if (randomDoubleNumber != CONSTANTS.DOUBLE_STRATEGY){
                    return new CollisionStrategy[]{randomDoublePickStrategy(
                            random.nextInt(CONSTANTS.TOTAL_DOUBLE_STRATEGIES) + CONSTANTS.ONE),
                            randomDoublePickStrategy
                                    (random.nextInt(CONSTANTS.TOTAL_DOUBLE_STRATEGIES) +
                                            CONSTANTS.ONE)};
                }
                else{
                    return new CollisionStrategy[]{randomDoublePickStrategy(
                            random.nextInt(CONSTANTS.TOTAL_DOUBLE_STRATEGIES) + CONSTANTS.ONE),
                            randomDoublePickStrategy
                                    (random.nextInt(CONSTANTS.TOTAL_DOUBLE_STRATEGIES) +
                                            CONSTANTS.ONE),
                            randomDoublePickStrategy
                            (random.nextInt(CONSTANTS.TOTAL_DOUBLE_STRATEGIES)
                                    + CONSTANTS.ONE)};
                }
            default:
                return new CollisionStrategy[]{remove()};
        }
    }
    /**
     * Picks a random double strategy based on the given random number.
     * @param randomNumber The random number used to select the strategy.
     * @return A CollisionStrategy representing the selected double strategy.
     */
    private CollisionStrategy randomDoublePickStrategy(int randomNumber) {
        switch (randomNumber) {
            case CONSTANTS.PADDLE_STRATEGY:
                return paddle();
            case CONSTANTS.PUCK_STRATEGY:
                return puck();
            case CONSTANTS.CAMERA_STRATEGY:
                return camera();
            case CONSTANTS.LIFE_STRATEGY:
                return life();
            default:
                return null;
        }
    }

    /**
     * Returns an array of CollisionStrategy objects representing random strategies.
     * @return An array of CollisionStrategy objects representing random strategies.
     */
    public CollisionStrategy[] getRandomStrategies() {
        Random random = new Random();
        // plus 1 to pick between 1-10
        return randomPickStrategy(random, random.nextInt(CONSTANTS.TOTAL_STRATEGIES) +
                CONSTANTS.ONE);
    }


}
