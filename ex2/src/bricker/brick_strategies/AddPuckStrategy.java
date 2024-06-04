package bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.util.Counter;
import danogl.util.Vector2;
import bricker.gameobjects.Ball;
import bricker.main.CONSTANTS;
import bricker.gameobjects.Puck;
import bricker.main.BrickerGameManager;

import java.util.Random;

/**
 * The AddPuckStrategy class implements the CollisionStrategy interface and defines a strategy
 * for adding a puck to the game when a collision occurs between the bricker.main ball and random brick.
 */
public class AddPuckStrategy implements CollisionStrategy {

    // Attributes
    private final GameObjectCollection gameObjects; // Collection of game objects
    private final ImageReader imageReader; // Reader for image resources
    private final SoundReader soundReader; // Reader for sound resources
    private final BrickerGameManager game; // Reference to the game
    private final Counter cameraResetCounter; // Counter for camera reset

    /**
     * Constructs an AddPuckStrategy object with the specified parameters.
     *
     * @param gameObjectsCollection  The collection of game objects.
     * @param imageReader            The image reader for loading resources.
     * @param soundReader            The sound reader for loading resources.
     * @param game                   The reference to the game.
     * @param cameraResetCounter     The counter for camera reset.
     */
    public AddPuckStrategy(GameObjectCollection gameObjectsCollection, ImageReader imageReader,
                           SoundReader soundReader, BrickerGameManager game, Counter cameraResetCounter) {
        this.gameObjects = gameObjectsCollection;
        this.imageReader = imageReader;
        this.soundReader = soundReader;
        this.game = game;
        this.cameraResetCounter = cameraResetCounter;
    }

    /**
     * Handles the collision between two game objects and adds pucks to the game at the collision position.
     *
     * @param firstCollisedGameObject   The first game object involved in the collision.
     * @param secondCollisedGameObject  The second game object involved in the collision.
     */
    @Override
    public void onCollision(GameObject firstCollisedGameObject, GameObject secondCollisedGameObject) {
        // Instantiate a BasicCollisionStrategy for handling the collision
        BasicCollisionStrategy basicCollisionStrategy = new BasicCollisionStrategy(gameObjects);
        basicCollisionStrategy.onCollision(firstCollisedGameObject, secondCollisedGameObject);

        // Add pucks to the game at the collision position
        for (int i = CONSTANTS.ZERO_INITIALIZE; i < CONSTANTS.NUM_PUCKS; i++) {
            Ball puck = new Puck(Vector2.ZERO,
                    CONSTANTS.BALL_DEFAULT_DIMENSION.mult(CONSTANTS.MOCK_BALL_SIZE),
                    imageReader.readImage(CONSTANTS.PUCK_IMAGE_PATH, true),
                    soundReader.readSound(CONSTANTS.BALL_SOUND_PATH), this.game, this.cameraResetCounter);
            puck.setTag(CONSTANTS.PUCK_TAG);

            Vector2 brickLocation = firstCollisedGameObject.getCenter();
            Vector2 brickDimensions = firstCollisedGameObject.getDimensions();

            // Set the puck's velocity to a randomly selected direction
            puck.setVelocity(getRandomBallDirection());

            // Set the puck's location in the center of the bricked brick .
            puck.setCenter(new Vector2(brickLocation.x() + (brickDimensions.x() / 2),
                    brickLocation.y()));

            gameObjects.addGameObject(puck);
        }
    }

    /**
     * Generates a random velocity vector representing the direction of the puck.
     *
     * @return A Vector2 representing the puck's velocity.
     */
    private Vector2 getRandomBallDirection() {
        // Generate a random number between 0 and 3 to represent the direction
        int randomDirection = new Random().nextInt(CONSTANTS.NUM_DIRECTIONS);

        // Define the velocities for each direction
        switch (randomDirection) {
            case CONSTANTS.RIGHT_DIRECTION:
                return new Vector2(CONSTANTS.BALL_SPEED, 0); // Right
            case CONSTANTS.LEFT_DIRECTION:
                return new Vector2(-CONSTANTS.BALL_SPEED, 0); // Left
            case CONSTANTS.DOWN_DIRECTION:
                return new Vector2(0, CONSTANTS.BALL_SPEED); // Down
            case CONSTANTS.UP_DIRECTION:
                return new Vector2(0, -CONSTANTS.BALL_SPEED); // Up
        }
        return null;
    }
}
