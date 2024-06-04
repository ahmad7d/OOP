package bricker.gameobjects;

import bricker.brick_strategies.CollisionStrategy;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import bricker.main.CONSTANTS;

/**
 * he Brick class manages brick objects in the game environment. It handles collision events with other game
 * elements and triggers various strategies based on the collision type. These strategies include adding
 * extra paddles, creating additional pucks, switching the game camera, executing double strategies,
 * and normal removal of the brick.
 * Each brick has its own strategy and location .
 */
public class Brick extends GameObject {
    private boolean isCollided = false;
    private final GameObjectCollection gameObjects;
    private final CollisionStrategy[] strategies;
    private final Counter liftBricks;

    /**
     * Constructs a new Brick object.
     *
     * @param topLeftCorner Position of the brick, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height of the brick in window coordinates.
     * @param gameObjects   The collection of game objects to which the brick belongs.
     * @param renderable   The image reader for loading brick images.
     * @param strategies    An array of collision strategies associated with the brick.
     * @param liftBricks    The counter representing the number of lifted bricks.
     */
    public Brick(Vector2 topLeftCorner, Vector2 dimensions,
                 GameObjectCollection gameObjects, Renderable renderable,
                 CollisionStrategy[] strategies, Counter liftBricks) {
        super(topLeftCorner, dimensions, renderable);
        this.gameObjects = gameObjects;
        this.strategies = strategies;
        this.liftBricks = liftBricks;
    }

    /**
     * Handles the collision event when another game object collides with the brick.
     *
     * @param other     The other game object involved in the collision.
     * @param collision The collision data associated with the collision event.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);

        // First of all removing the brick object from the board to avoid another collide with it .
        gameObjects.removeGameObject(this);

        // At times, when two balls collide with the brick simultaneously, this method may be invoked twice
        // . Consequently, the liftBricks.decrement() operation also executes twice. To address this,  we
        // initialize the isCollided flag for each object. Thus, before executing the method logic,  we
        // check if the flag is true and prevent the decrement operation from occurring multiple times.
        if (isCollided) {
            return;
        }
        isCollided = true;

        // Apply collision strategies associated with the brick , might be 1, 2 or 3 strategies in the same
        // brick .
        for (CollisionStrategy strategy : strategies) {
            strategy.onCollision(this, other);
        }

        // Decrement the counter representing the number of bricks lifted
        liftBricks.decrement();
    }
}
