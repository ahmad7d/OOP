package bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Camera;
import danogl.util.Counter;
import danogl.util.Vector2;
import bricker.gameobjects.Ball;
import bricker.main.CONSTANTS;
import bricker.main.BrickerGameManager;


/**
 * The AddCameraStrategy class implements the CollisionStrategy interface and defines a strategy for adding
 * a camera to the game when a collision occurs between the bricker.main ball and random brick.
 */
public class AddCameraStrategy implements CollisionStrategy {

    // Attributes
    private final GameObjectCollection gameObjects; // Collection of game objects
    private final BrickerGameManager game; // Reference to the game instance
    private final Vector2 windowDimensions; // Dimensions of the game window
    private final Counter cameraResetCounter; // Counter for resetting the camera

    /**
     * Constructs an AddCameraStrategy object with the specified parameters.
     *
     * @param gameObjectCollection The collection of game objects.
     * @param game                 The Game instance.
     * @param windowDimensions     The dimensions of the game window.
     * @param cameraResetCounter  The counter for resetting the camera.
     */
    public AddCameraStrategy(GameObjectCollection gameObjectCollection, BrickerGameManager game,
                             Vector2 windowDimensions, Counter cameraResetCounter) {
        this.gameObjects = gameObjectCollection;
        this.game = game;
        this.windowDimensions = windowDimensions;
        this.cameraResetCounter = cameraResetCounter;
    }

    /**
     * Handles the collision between two game objects and adds a camera to the game if a collision with the
     * ball occurs.
     *
     * @param firstCollisedGameObject  The first game object involved in the collision.
     * @param secondCollisedGameObject The second game object involved in the collision.
     */
    @Override
    public void onCollision(GameObject firstCollisedGameObject, GameObject secondCollisedGameObject) {
        // Instantiate a BasicCollisionStrategy for handling the collision
        BasicCollisionStrategy basicCollisionStrategy = new BasicCollisionStrategy(gameObjects);
        basicCollisionStrategy.onCollision(firstCollisedGameObject, secondCollisedGameObject);

        // Check if the second collided game object is a ball and add a camera accordingly
        if (secondCollisedGameObject.getTag().equals(CONSTANTS.BALL_TAG)) {
            if (this.game.camera() == null) { // Check if a camera is not already present
                // Increase the camera reset counter by 4 (which it's the maximum Ball collisions times to
                // reset the camera)
                this.cameraResetCounter.increaseBy(CONSTANTS.NUM_BALL_COLLISION);
                // Set the game's camera to follow the ball with a widened frame
                this.game.setCamera(createCamera((Ball) secondCollisedGameObject));
            }
        }
    }

    /**
     * Creates and returns a new Camera instance based on the specified ball object.
     *
     * @param ball The ball object to follow with the camera.
     * @return A new Camera instance configured to follow the specified ball.
     */
    private Camera createCamera(Ball ball) {
        return new Camera(
                ball, // Object to follow
                Vector2.ZERO, // Follow the center of the object
                this.windowDimensions.mult(CONSTANTS.CAMERA_ZOOM_IN), // Widen the frame
                this.windowDimensions // Share the window dimensions
        );
    }
}
