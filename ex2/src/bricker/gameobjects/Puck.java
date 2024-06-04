package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import bricker.main.BrickerGameManager;


/**
 * Represents a puck object in the game, which is a type of ball.
 * Pucks have specific Ball features like (smaller dimensions , another image).
 */
public class Puck extends Ball {

    private final Sound soundCollision;

    /**
     * Constructs a new Puck object.
     *
     * @param topLeftCorner      Position of the object, in window coordinates (pixels).
     *                           Note that (0,0) is the top-left corner of the window.
     * @param dimensions         Width and height in window coordinates.
     * @param renderable         The renderable representing the object.
     * @param soundCollision     The sound played upon collision.
     * @param game               The game instance.
     * @param cameraResetCounter The counter for camera reset.
     */
    public Puck(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Sound soundCollision,
                BrickerGameManager game, Counter cameraResetCounter) {
        super(topLeftCorner, dimensions, renderable, soundCollision, game, cameraResetCounter);
        this.soundCollision = soundCollision;
    }

    /**
     * Handles collision events with other game objects.
     * When a collision occurs, the puck reverses its velocity based on the collision normal
     * and plays the collision sound.
     *
     * @param other     The other game object involved in the collision.
     * @param collision The collision details.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        // Reverse the velocity of the puck based on the collision normal
        setVelocity(getVelocity().flipped(collision.getNormal()));

        // Play the collision sound
        this.soundCollision.play();
    }
}
