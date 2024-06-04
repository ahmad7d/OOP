package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.util.Vector2;
import bricker.main.CONSTANTS;


/**
 * The FallingHeart class represents a heart-shaped power-up that falls from the top of the game screen
 * towards the player-controlled paddle. When the player's paddle collides with the falling heart,  the
 * player gains an additional life. Falling hearts are visual indicators of life power-ups within the game.
 * This class handles the behavior of the falling hearts, including their movement, collision detection with
 * the paddle, and the logic for increasing the player's life count upon collision.
 */
public class FallingHeart extends Heart {
    private final ImageReader imageReader;
    private Heart[] lifeCounterList;
    private final GameObjectCollection gameObjectCollection;

    /**
     * Constructs a new FallingHeart instance.
     *
     * @param topLeftCorner       Position of the object, in window coordinates (pixels).
     *                            Note that (0,0) is the top-left corner of the window.
     * @param dimensions          Width and height in window coordinates.
     * @param imageReader         The ImageReader representing the object. Can be null, in which case
     *                            the GameObject will not be rendered.
     * @param lifeCounterList     Array representing the player's life counters.
     * @param gameObjectCollection Collection of game objects.
     */
    public FallingHeart(Vector2 topLeftCorner, Vector2 dimensions, ImageReader imageReader,
                        Heart[] lifeCounterList, GameObjectCollection gameObjectCollection) {
        super(topLeftCorner, dimensions, imageReader);
        this.imageReader = imageReader;
        this.lifeCounterList = lifeCounterList;
        this.gameObjectCollection = gameObjectCollection;
    }

    /**
     * Handles collision with other game objects.
     *
     * @param other     The other game object involved in the collision.
     * @param collision The collision object describing the collision.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        if (other.getTag().equals(CONSTANTS.PADDLE_TAG)) {
            if (lifeCounterList[lifeCounterList.length - 1] == null) {
                increaseLives();
            }
            gameObjectCollection.removeGameObject(this);
        }
    }

    /**
     * Increases the player's lives.
     */
    private void increaseLives() {
        int i = CONSTANTS.ZERO_INITIALIZE; // Start from the first index
        while (i < lifeCounterList.length && lifeCounterList[i] != null) {
            i++; // Move towards the end until finding a null element
        }
        // Add a new heart if there's space in the array
        if (lifeCounterList[i] == null) {
            Heart newHeart = new Heart(
                    new Vector2(i * CONSTANTS.HEART_SHIFT_LOCATION, CONSTANTS.HEART_HEIGHT_LOCATION),
                    CONSTANTS.HEART_DEFAULT_DIMENSIONS, imageReader);
            gameObjectCollection.addGameObject(newHeart, Layer.UI);
            lifeCounterList[i] = newHeart;
        }
    }
}
