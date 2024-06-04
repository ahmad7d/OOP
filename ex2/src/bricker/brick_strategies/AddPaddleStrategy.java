package bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.UserInputListener;
import danogl.util.Counter;
import danogl.util.Vector2;
import bricker.gameobjects.LimitedPaddle;
import bricker.main.CONSTANTS;

/**
 * The AddPaddleStrategy class implements the CollisionStrategy interface and defines a strategy for
 * adding a paddle to the game when a collision occurs between the bricker.main ball and random brick.
 */
public class AddPaddleStrategy implements CollisionStrategy {

    // Attributes
    private final GameObjectCollection gameObjects; // Collection of game objects
    private final ImageReader imageReader; // Reader for image resources
    private final UserInputListener userInputListener; // Listener for user input
    private final Vector2 windowDimensions; // Dimensions of the game window
    private final Counter limitedPaddleCollisionsCounter; // Counter for limited paddle collisions

    /**
     * Constructs an AddPaddleStrategy object with the specified parameters.
     *
     * @param gameObjects                     The collection of game objects.
     * @param imageReader                     The image reader for loading resources.
     * @param userInputListener               The user input listener for paddle control.
     * @param windowDimensions                The dimensions of the game window.
     * @param limitedPaddleCollisionsCounter  The counter for limited paddle collisions.
     */
    public AddPaddleStrategy(GameObjectCollection gameObjects, ImageReader imageReader,
                             UserInputListener userInputListener, Vector2 windowDimensions,
                             Counter limitedPaddleCollisionsCounter) {
        this.gameObjects = gameObjects;
        this.imageReader = imageReader;
        this.userInputListener = userInputListener;
        this.windowDimensions = windowDimensions;
        this.limitedPaddleCollisionsCounter = limitedPaddleCollisionsCounter;
    }

    /**
     * Handles the collision between the ball and brick with this strategy, and adds an extra paddle to the
     * game if the strategy isn't in use.
     *
     * @param firstCollisedGameObject   The first game object involved in the collision.
     * @param secondCollisedGameObject  The second game object involved in the collision.
     */
    @Override
    public void onCollision(GameObject firstCollisedGameObject, GameObject secondCollisedGameObject) {
        // Instantiate a BasicCollisionStrategy for handling the collision
        BasicCollisionStrategy basicCollisionStrategy = new BasicCollisionStrategy(gameObjects);
        basicCollisionStrategy.onCollision(firstCollisedGameObject, secondCollisedGameObject);

        // Check if a limited paddle is available
        if (this.limitedPaddleCollisionsCounter.value() == CONSTANTS.LIMITED_PADDLE_AVAILABLE_COUNTER) {
            // Create an extra paddle and set its attributes
            GameObject extraPaddle = new LimitedPaddle(Vector2.ZERO, CONSTANTS.PADDLE_DEFAULT_DIMENSIONS,
                    imageReader, userInputListener, windowDimensions, limitedPaddleCollisionsCounter,
                    gameObjects);
            extraPaddle.setCenter(CONSTANTS.LIMITED_PADDLE_LOCATION);

            // Add the extra paddle to the game objects collection
            gameObjects.addGameObject(extraPaddle);
        }
    }
}
