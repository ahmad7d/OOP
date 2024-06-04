package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.UserInputListener;
import danogl.util.Counter;
import danogl.util.Vector2;
import bricker.main.CONSTANTS;


/**
 * Represents a limited paddle object in the game, which is controlled by user input.
 * The paddle moves horizontally based on user input and interacts with other game objects.
 * The limited paddle has 4 times of collisions allowed before it is removed from the game.
 */
public class LimitedPaddle extends Paddle {
    private final Counter limitedPaddleCollisionCounter;
    private final GameObjectCollection gameObjects;

    /**
     * Constructs a new LimitedPaddle instance.
     *
     * @param topLeftCorner                 Position of the object, in window coordinates (pixels).
     *                                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions                    Width and height in window coordinates.
     * @param imageReader                   The ImageReader representing the object. Can be null, in
     *                                     which case
     *                                      the GameObject will not be rendered.
     * @param inputListener                 The UserInputListener representing the object. Can be null,
     *                                     in which case
     *                                      the GameObject will not be controlled by the user.
     * @param windowDimensions              The dimensions of the game window.
     * @param limitedPaddleCollisionCounter The counter for limited paddle collisions.
     * @param gameObjects                   The collection of game objects.
     */
    public LimitedPaddle(Vector2 topLeftCorner, Vector2 dimensions, ImageReader imageReader,
                         UserInputListener inputListener, Vector2 windowDimensions,
                         Counter limitedPaddleCollisionCounter, GameObjectCollection gameObjects) {
        super(topLeftCorner, dimensions, imageReader, inputListener, windowDimensions);
        this.limitedPaddleCollisionCounter = limitedPaddleCollisionCounter;

        // We increment the counter by 1 to prevent the creation of two limited paddles simultaneously. This
        // method is only called when the counter equals 0. By incrementing it upon entering the
        // constructor,  we ensure that it counts up to 5, allowing for 4 collisions.
        this.limitedPaddleCollisionCounter.increment();

        this.gameObjects = gameObjects;
    }

    /**
     * Determines whether the LimitedPaddle should collide with the given GameObject.
     *
     * @param other The GameObject to check collision with.
     * @return True if the LimitedPaddle should collide with the GameObject, false otherwise.
     */
    @Override
    public boolean shouldCollideWith(GameObject other) {
        return super.shouldCollideWith(other) &&
                (other.getTag().equals(CONSTANTS.BALL_TAG) ||
                        other.getTag().equals(CONSTANTS.PUCK_TAG));
    }

    /**
     * Handles the collision event when the LimitedPaddle collides with another GameObject.
     *
     * @param other     The GameObject collided with.
     * @param collision The collision details.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);

        // Handle it only if the collided object Ball or Puck
        if (other.getTag().equals(CONSTANTS.BALL_TAG) || other.getTag().equals(CONSTANTS.PUCK_TAG)) {

            // Increase the limitedPaddleCollisionCounter by 1
            this.limitedPaddleCollisionCounter.increment();

            // If the limitedPaddle collisions times reached the max allowed times , remove the paddle and
            // reset the counter to use it for another same strategy .
            if (this.limitedPaddleCollisionCounter.value() == CONSTANTS.MAX_LIMITED_PADDLE_COLLISIONS_TIMES) {
                this.limitedPaddleCollisionCounter.reset();
                gameObjects.removeGameObject(this);
            }
        }
    }
}
