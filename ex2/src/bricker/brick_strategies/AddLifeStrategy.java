package bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.util.Vector2;
import bricker.gameobjects.FallingHeart;
import bricker.gameobjects.Heart;
import bricker.main.CONSTANTS;

/**
 * The AddLifeStrategy class implements the CollisionStrategy interface and defines a strategy for adding a
 * life (heart) to the game when a collision occurs between the bricker.main ball and random brick.
 */
public class AddLifeStrategy implements CollisionStrategy {

    // Attributes
    private final GameObjectCollection gameObjects; // Collection of game objects
    private final ImageReader imageReader; // Reader for image resources
    private final Heart[] lifeCountersList; // Array of life counters

    /**
     * Constructs an AddLifeStrategy object with the specified parameters.
     *
     * @param gameObjectCollection The collection of game objects.
     * @param imageReader          The image reader for loading resources.
     * @param lifeCountersList     The array of life counters.
     */
    public AddLifeStrategy(GameObjectCollection gameObjectCollection, ImageReader imageReader,
                           Heart[] lifeCountersList) {
        this.gameObjects = gameObjectCollection;
        this.imageReader = imageReader;
        this.lifeCountersList = lifeCountersList;
    }

    /**
     * Handles the collision between two game objects and adds a falling heart to the game.
     *
     * @param firstCollisedGameObject  The first game object involved in the collision.
     * @param secondCollisedGameObject The second game object involved in the collision.
     */
    @Override
    public void onCollision(GameObject firstCollisedGameObject, GameObject secondCollisedGameObject) {
        // Instantiate a BasicCollisionStrategy for handling the collision
        BasicCollisionStrategy basicCollisionStrategy = new BasicCollisionStrategy(gameObjects);
        basicCollisionStrategy.onCollision(firstCollisedGameObject, secondCollisedGameObject);

        // Create a falling heart and set its attributes
        Heart fallingHeart = new FallingHeart(Vector2.ZERO, CONSTANTS.HEART_DEFAULT_DIMENSIONS,
                imageReader, lifeCountersList, gameObjects);

        fallingHeart.setVelocity(Vector2.DOWN.multY(CONSTANTS.HEART_SPEED));

        // Set the heart's position using setCenter method for accuracy , We used the setCenter method instead
        // of passing the required location for the topLeftCorner parameter because it's more accurate.
        fallingHeart.setCenter(firstCollisedGameObject.getCenter());
        fallingHeart.setTag(CONSTANTS.FALLING_HEART_TAG);

        // Add the falling heart to the game objects collection
        gameObjects.addGameObject(fallingHeart);
    }
}
