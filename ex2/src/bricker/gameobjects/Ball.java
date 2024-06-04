package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import bricker.main.CONSTANTS;
import bricker.main.BrickerGameManager;

/**
 * The Ball class represents a ball object in the game. It inherits properties and behaviors from the
 * GameObject class. The ball interacts with other game objects and handles collision events.
 * When a collision occurs, the ball changes its velocity based on the collision normal and plays a
 * collision sound.
 */
public class Ball extends GameObject {
    private final Sound soundCollision; // The sound played upon collision
    private final BrickerGameManager game; // The game instance
    private final Counter cameraResetCounter; // Counter for resetting the camera
    private final Counter collisionCounter =  new Counter(CONSTANTS.ZERO_INITIALIZE); // Counter for ball
    // collisions

    /**
     * Constructs a new Ball object.
     *
     * @param topLeftCorner      The position of the top-left corner of the ball, in window coordinates
     *                          (pixels).
     * @param dimensions         The width and height of the ball in window coordinates.
     * @param renderable         The renderable object for rendering the ball.
     * @param soundCollision     The sound played upon collision with other objects.
     * @param game               The game instance.
     * @param cameraResetCounter The counter for resetting the camera.
     */
    public Ball(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Sound soundCollision,
                BrickerGameManager game, Counter cameraResetCounter) {
        super(topLeftCorner, dimensions, renderable);
        this.soundCollision = soundCollision;
        this.game = game;
        this.cameraResetCounter = cameraResetCounter;
    }

    /**
     * Handles actions upon collision with other game objects.
     *
     * @param other     The other game object involved in the collision.
     * @param collision The collision details.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);

        // Increase ball collisions counter
        collisionCounter.increment();

        // Do not handle collision if colliding with a falling heart
        if (other.getTag().equals(CONSTANTS.FALLING_HEART_TAG)) {
            return;
        }

        // Reflect the velocity of the ball based on collision normal
        setVelocity(getVelocity().flipped(collision.getNormal()));

        // Play the collision sound
        this.soundCollision.play();

        // Handle camera reset
        if (cameraResetCounter.value() > CONSTANTS.ZERO) {
            if (cameraResetCounter.value() == CONSTANTS.MAX_COLLIDE_TIMES_TO_RESET_CAMERA) {
                game.setCamera(null);
            }
            // decrease the counter
            cameraResetCounter.decrement();
        }
    }

    /**
     * Retrieves the value of the collision counter.
     * @return The value of the collision counter.
     */
    public int getCollisionCounter(){
        return collisionCounter.value();
    }



}
