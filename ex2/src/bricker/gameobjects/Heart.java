package bricker.gameobjects;

import danogl.GameObject;
import danogl.gui.ImageReader;
import danogl.util.Vector2;
import bricker.main.CONSTANTS;


/**
 * The Heart class represents a heart-shaped object used in the game. It serves as a visual representation
 * of  the player's remaining lives. Each heart displayed on the user interface corresponds to one life of
 * the player. When a player loses a life, one of the hearts disappears from the screen. The Heart class is
 * responsible for rendering the heart image on the game screen and managing its position, dimensions and
 * amounts .
 */
public class Heart extends GameObject {

    /**
     * Constructs a new Heart instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param imageReader   The ImageReader representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     */
    public Heart(Vector2 topLeftCorner, Vector2 dimensions, ImageReader imageReader) {
        super(topLeftCorner, dimensions,
                imageReader.readImage(CONSTANTS.HEART_IMAGE_PATH, CONSTANTS.TRANSPARENCY_MODE_ON));
    }
}
