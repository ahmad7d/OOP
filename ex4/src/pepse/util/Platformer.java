package pepse.util;

import danogl.*;
import danogl.collisions.Layer;
import danogl.components.*;
import danogl.gui.*;
import danogl.gui.rendering.*;
import danogl.util.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * A simple platformer demo with a circle as an avatar and a few platforms.
 * Move with left and right keys, jump with space, down+space to drop down a platform.
 * @author Dan Nirel
 */
public class Platformer extends GameManager {
    private static final Color BACKGROUND_COLOR = Color.decode("#80C6E5");
    private static final Color PLATFORM_COLOR = new Color(212, 123, 74);

    /**
     *
     * @param imageReader Contains a single method: readImage, which reads an image from disk.
     *                 See its documentation for help.
     * @param soundReader Contains a single method: readSound, which reads a wav file from
     *                    disk. See its documentation for help.
     * @param inputListener Contains a single method: isKeyPressed, which returns whether
     *                      a given key is currently pressed by the user or not. See its
     *                      documentation.
     * @param windowController Contains an array of helpful, self explanatory methods
     *                         concerning the window.
     */
    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader, UserInputListener
            inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);

        //background
        var background =
            new GameObject(
                    Vector2.ZERO,
                    windowController.getWindowDimensions(),
                    new RectangleRenderable(BACKGROUND_COLOR)
            );
        background.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        gameObjects().addGameObject(background, Layer.BACKGROUND);

        placePlatform(Vector2.of(-1024, 1000), Vector2.ONES.mult(2048));
        placePlatform(Vector2.of(-512, 700), Vector2.of(1024, 50));
        placePlatform(Vector2.of(-256, 400), Vector2.of(512, 50));
        placePlatform(Vector2.of(-128, 100), Vector2.of(256, 50));

        var avatar = new Avatar(Vector2.of(0, 900), inputListener);
        setCamera(new Camera(avatar, Vector2.ZERO,
                windowController.getWindowDimensions(), windowController.getWindowDimensions()));
        gameObjects().addGameObject(avatar);
    }

    private void placePlatform(Vector2 pos, Vector2 size) {
        var platform = new GameObject(pos, size, new RectangleRenderable(PLATFORM_COLOR));
        platform.physics().preventIntersectionsFromDirection(Vector2.UP);
        platform.physics().setMass(GameObjectPhysics.IMMOVABLE_MASS);
        gameObjects().addGameObject(platform, Layer.STATIC_OBJECTS);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        new Platformer().run();
    }
}

/**
 *
 */
class Avatar extends GameObject {
    private static final float VELOCITY_X = 400;
    private static final float VELOCITY_Y = -650;
    private static final float GRAVITY = 600;
    private static final Color AVATAR_COLOR = Color.DARK_GRAY;

    private UserInputListener inputListener;

    public Avatar(Vector2 pos, UserInputListener inputListener) {
        super(pos, Vector2.ONES.mult(50), new OvalRenderable(AVATAR_COLOR));
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        transform().setAccelerationY(GRAVITY);
        this.inputListener = inputListener;
    }

    /**
     *
     * @param deltaTime The time elapsed, in seconds, since the last frame. Can
     *                  be used to determine a new position/velocity by multiplying
     *                  this delta with the velocity/acceleration respectively
     *                  and adding to the position/velocity:
     *                  velocity += deltaTime*acceleration
     *                  pos += deltaTime*velocity
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float xVel = 0;
        if(inputListener.isKeyPressed(KeyEvent.VK_LEFT))
            xVel -= VELOCITY_X;
        if(inputListener.isKeyPressed(KeyEvent.VK_RIGHT))
            xVel += VELOCITY_X;
        transform().setVelocityX(xVel);
        if(inputListener.isKeyPressed(KeyEvent.VK_SPACE) && getVelocity().y() == 0)
            transform().setVelocityY(VELOCITY_Y);
    }
}