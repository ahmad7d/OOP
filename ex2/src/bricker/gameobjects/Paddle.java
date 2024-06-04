package bricker.gameobjects;

import danogl.GameObject;
import danogl.gui.ImageReader;
import danogl.gui.UserInputListener;
import danogl.util.Vector2;
import bricker.main.CONSTANTS;

import java.awt.event.KeyEvent;
import java.util.Set;


/**
 * Represents a paddle object in the game, which is controlled by user input.
 * The paddle moves horizontally based on user input and interacts with other game objects.
 */
public class Paddle extends GameObject {
    private final UserInputListener inputListener;
    private final Vector2 windowDimensions;

    /**
     * Constructs a new Paddle object.
     *
     * @param topLeftCorner    Position of the object, in window coordinates (pixels).
     *                         Note that (0,0) is the top-left corner of the window.
     * @param dimensions       Width and height in window coordinates.
     * @param imageReader      The image reader representing the object. Can be null, in which case
     *                         the GameObject will not be rendered.
     * @param inputListener    The input listener representing the object. Can be null, in which case
     *                         the GameObject will not be controlled by the user.
     * @param windowDimensions The dimensions of the game window.
     */
    public Paddle(Vector2 topLeftCorner, Vector2 dimensions, ImageReader imageReader,
                  UserInputListener inputListener, Vector2 windowDimensions) {
        super(topLeftCorner, dimensions, imageReader.readImage(CONSTANTS.PADDLE_IMAGE_PATH,
                CONSTANTS.TRANSPARENCY_MODE_OF));

        this.inputListener = inputListener;
        this.windowDimensions = windowDimensions;
    }

    /**
     * Updates the paddle's position based on user input.
     *
     * @param deltaTime The time elapsed since the last update, in seconds.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        Vector2 movement = Vector2.ZERO;
        Set<Integer> lastPressedKeys = inputListener.pressedKeys();

        // Iterate over the set to check the pressed keys
        for (Integer key : lastPressedKeys) {
            switch (key) {
                case KeyEvent.VK_LEFT:
                    // if the paddle coordinates isn't out of board , move the paddle .
                    if (isValidLeftMove()) {
                        movement = movement.add(Vector2.LEFT);
                        break;
                    }
                case KeyEvent.VK_RIGHT:
                    // if the paddle coordinates isn't out of board , move the paddle .
                    if (isValidRightMove()){
                        movement = movement.add(Vector2.RIGHT);
                        break;
                    }
            }
        }
        // Set the velocity based on the movement vector and the movement speed constant
        setVelocity(movement.mult(CONSTANTS.PADDLE_MOVEMENT_SPEED));
    }

    /**
     * Check's if the paddle reached the topRight board edges
     * @return true if the paddle coordinated didn't reach the board edges else false
     */
    private boolean isValidRightMove(){
        // We are decreasing the width of the paddle / 2 to get the topLeft coordinate , same way with
        // increasing to get topRight coordinate of the paddle .
        return this.getCenter().x() + (CONSTANTS.PADDLE_DEFAULT_SIZE.x() / 2) <= windowDimensions.x();
    }

    /**
     * Check's if the paddle reached the topLeft board edges
     * @return true if the paddle coordinated didn't reach the board edges else false
     */
    private boolean isValidLeftMove(){
        return this.getCenter().x() - (CONSTANTS.PADDLE_DEFAULT_SIZE.x() / 2) >= CONSTANTS.ZERO;
    }

}






