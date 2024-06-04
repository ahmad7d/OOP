package bricker.brick_strategies;

import danogl.GameObject;

/**
 * The CollisionStrategy interface defines the contract for collision strategies in the brick game.
 */
public interface CollisionStrategy {

    /**
     * Handles the collision between two game objects.
     *
     * @param firstCollisedGameObject  The first game object involved in the collision.
     * @param secondCollisedGameObject The second game object involved in the collision.
     */
    void onCollision(GameObject firstCollisedGameObject, GameObject secondCollisedGameObject);
}
