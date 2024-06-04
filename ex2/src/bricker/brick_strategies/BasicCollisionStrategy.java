package bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;

/**
 * The BasicCollisionStrategy class implements the CollisionStrategy interface and defines
 * a basic collision strategy for handling collisions between the bricker.main ball and random brick.
 */
public class BasicCollisionStrategy implements CollisionStrategy {

    // Attributes
    private final GameObjectCollection gameObjects; // Collection of game objects

    /**
     * Constructs a BasicCollisionStrategy object with the specified game object collection.
     *
     * @param gameObjects The collection of game objects.
     */
    public BasicCollisionStrategy(GameObjectCollection gameObjects) {
        this.gameObjects = gameObjects;
    }

    /**
     * Handles the collision between two game objects by removing the first collided object from the game.
     *
     * @param firstCollisedGameObject  The first game object involved in the collision.
     * @param secondCollisedGameObject The second game object involved in the collision.
     */
    @Override
    public void onCollision(GameObject firstCollisedGameObject, GameObject secondCollisedGameObject) {
        // Remove the first collided game object from the game
        this.gameObjects.removeGameObject(firstCollisedGameObject);
    }
}
