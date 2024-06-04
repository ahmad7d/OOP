package pepse.world;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.ImageReader;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.AnimationRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.Constants;
import pepse.world.trees.ObserverOfJump;

import java.awt.event.KeyEvent;
import java.util.*;
/**
 * The Avatar class represents a character or avatar within a game environment.
 * It handles user input, collision detection, and updates the avatar's state accordingly.
 */
public class Avatar extends GameObject {

    // List of observers for jump events
    private final List<ObserverOfJump> observersOfJump = new ArrayList<>();

    // User input listener for controlling the avatar
    private final UserInputListener inputListener;

    // Image reader for reading avatar's graphical assets
    private final ImageReader imageReader;

    // Energy counter for managing avatar's energy level
    private final Energy energyCounter;


    // Flags to track avatar's state
    private boolean isCollidedWithBlock;
    private boolean isMoving;
    private boolean collTrunk;


    // 3 types of Avatar animation
    private AnimationRenderable runAnimation;
    private AnimationRenderable jumpAnimation;
    private AnimationRenderable idleAnimation;


    /**
     * Constructs an Avatar object with the given parameters.
     *
     * @param pos           Initial position of the avatar
     * @param inputListener User input listener for controlling the avatar
     * @param imageReader   Image reader for reading avatar's graphical assets
     * @param energy        Energy counter for managing avatar's energy level
     */
    public Avatar(Vector2 pos, UserInputListener inputListener, ImageReader imageReader, Energy energy) {
        super(pos, Constants.AVATAR_DEFAULT_SIZE, imageReader.readImage(Constants.IMAGE_PATH ,
                Constants.TRANSPARENCY_MODE_ON));
        this.inputListener = inputListener;
        this.imageReader = imageReader;
        this.energyCounter = energy;
        this.transform().setAccelerationY(Constants.GRAVITY);
        this.isCollidedWithBlock = true;
        this.isMoving = false;
        this.collTrunk = false;
        this.physics().preventIntersectionsFromDirection(Vector2.ZERO);
        this.setTag(Constants.AVATAR_TAG);
        initializeAnimations();
    }

    /**
     * Initializes the avatar animations.
     */
    private void initializeAnimations() {
        runAnimation = createAnimation(Constants.RUN_IMAGE_TYPE, Constants.RUN_IMAGES,
                Constants.RUN_TIME_BETWEEN_CLIPS);
        jumpAnimation = createAnimation(Constants.JUMP_IMAGE_TYPE, Constants.JUMP_IMAGES,
                Constants.JUMP_TIME_BETWEEN_CLIPS);
        idleAnimation = createAnimation(Constants.IDLE_IMAGE_TYPE, Constants.IDLE_IMAGES,
                Constants.IDLE_TIME_BETWEEN_CLIPS);
    }

    /**
     * Creates an animation renderable for the specified animation type and number of images.
     *
     * @param animationType    Type of animation (e.g., "run", "idle", "jump")
     * @param numImages        Number of images in the animation
     * @param timeBetweenClips Time to switch between each image
     * @return AnimationRenderable object for the specified animation
     */
    private AnimationRenderable createAnimation(String animationType, int numImages, double timeBetweenClips){
        Renderable[] renderables = new Renderable[numImages];
        for (int i = 0; i < numImages; i++) {
            String imagePath = String.format(Constants.IMAGE_PATH_FORMAT, animationType, i);
            renderables[i] = imageReader.readImage(imagePath, Constants.TRANSPARENCY_MODE_ON);
        }
        return new AnimationRenderable(renderables, timeBetweenClips);
    }

    /**
     * Updates the avatar's state based on user input and game conditions.
     *
     * @param deltaTime Time elapsed since the last update
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        // Initialize horizontal velocity
        float xVel = Constants.ZERO_INIT;
        // Iterate over pressed keys
        for (Integer key : inputListener.pressedKeys()) {
            switch (key) {
                // Moving left or right
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_RIGHT:
                    // Check if there is enough energy to move
                    if (this.energyCounter.getEnergyCounter() >= Constants.MOVEMENT_DECREMENT) {
                        this.isMoving = true;
                        // Update velocity based on pressed key
                        xVel += (key == KeyEvent.VK_LEFT) ? -Constants.VELOCITY_X : Constants.VELOCITY_X;
                        // Decrease energy
                        this.energyCounter.moveEnergyDecrease();
                    }
                    break;
                // Jumping
                case KeyEvent.VK_SPACE:
                    if (canJump()) {
                        performJump();
                    }
                    break;
            }
        }
        // Restore energy if avatar is on ground and below initial energy level
        if (this.isCollidedWithBlock && this.energyCounter.getEnergyCounter() < Constants.ENERGY_INIT) {
            this.energyCounter.restEnergyIncrease();
        }
        // Apply horizontal velocity
        transform().setVelocityX(xVel);
        // Restore energy if not moving and above initial energy level
        if (this.energyCounter.getEnergyCounter() > Constants.ENERGY_INIT) {
            this.energyCounter.resetEnergyCounter();
        }
        this.updateAnimation();
    }


    /**
     * Checks if the avatar can perform a jump.
     *
     * @return True if the avatar can jump, false otherwise
     */
    private boolean canJump() {
        return (isCollidedWithBlock || collTrunk) && energyCounter.getEnergyCounter() >=
                Constants.JUMP_INCREMENT;
    }

    /**
     * Performs a jump action.
     */
    private void performJump() {
        collTrunk = false;
        isCollidedWithBlock = false;
        notifyJumpObservers(observersOfJump);
        transform().setVelocityY(Constants.VELOCITY_Y);
        energyCounter.jumpEnergyDecrease();
    }


    /**
     * Updates the avatar's animation based on its velocity.
     * If the avatar is moving vertically (jumping), displays the jump animation.
     * If the avatar is moving horizontally to the left, displays the run animation facing left.
     * If the avatar is moving horizontally to the right, displays the run animation facing right.
     * If the avatar is stationary, displays the idle animation.
     * Flips the animation horizontally if moving to the left, and resets the flip if moving to the right.
     */
    private void updateAnimation() {
        // Check if the avatar is jumping (moving vertically)
        if (this.getVelocity().y() != 0) {
            this.renderer().setRenderable(jumpAnimation);
        }
        // Check if the avatar is moving horizontally to the left
        else if (this.getVelocity().x() < 0) {
            this.renderer().setRenderable(runAnimation);
            this.renderer().setIsFlippedHorizontally(Constants.FLIPPABLE);
        }
        // Check if the avatar is moving horizontally to the right
        else if (this.getVelocity().x() > 0) {
            this.renderer().setRenderable(runAnimation);
            this.renderer().setIsFlippedHorizontally(Constants.UNFLIPPABLE);
        }
        // Avatar is stationary
        else {
            this.renderer().setRenderable(idleAnimation);
        }

        // Flip animation horizontally if moving to the left, reset flip if moving to the right
        if (this.getVelocity().x() < 0) {
            this.renderer().setIsFlippedHorizontally(Constants.FLIPPABLE);
        } else if (this.getVelocity().x() > 0) {
            this.renderer().setIsFlippedHorizontally(Constants.UNFLIPPABLE);
        }
    }


    /**
     * Handles collision events with other game objects.
     *
     * @param other     The other game object involved in the collision
     * @param collision The collision details
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);

        if (other.getTag().equals(Constants.BLOCK_TAG)) {
            this.setVelocity(Vector2.ZERO);
            physics().preventIntersectionsFromDirection(Vector2.ZERO);
//            physics().setMass(GameObjectPhysics.IMMOVABLE_MASS);
            this.isCollidedWithBlock = true;
        }
        if (other.getTag().equals(Constants.TRUNK_TAG)){
            this.collTrunk=true;
        }
    }
    /**
     * Adds an observer for jump events to the list.
     *
     * @param observerOfJump Observer for jump events
     */
    public void addJumpObserverToList(ObserverOfJump observerOfJump){
        observersOfJump.add(observerOfJump);
    }
    /**
     * Notifies all jump observers upon a jump event.
     *
     * @param observersOfJump List of jump observers
     */
    private void notifyJumpObservers(List<ObserverOfJump> observersOfJump){
        for(ObserverOfJump observer : observersOfJump){
            observer.UponJump();
        }
    }
}
