package pepse.world.trees;

import danogl.GameObject;
import danogl.components.ScheduledTask;
import danogl.components.Transition;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.Constants;
import pepse.world.Avatar;

import java.util.Random;
/**
 * Class representing a leaf object in the game.
 */
public class Leaf extends GameObject implements ObserverOfJump {
    private static final Random random = new Random();
    private final Avatar avatarObject;

    /**
     * Constructs a new Leaf instance.
     *
     * @param topLeftCorner  The position of the leaf in window coordinates (pixels).
     * @param dimensions     The width and height of the leaf in window coordinates.
     * @param renderable     The renderable representing the leaf.
     * @param avatarObject   The avatar object.
     */
    public Leaf(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Avatar avatarObject) {
        super(topLeftCorner, dimensions, renderable);
        this.avatarObject = avatarObject;

        // Schedule random angle transition
        angleSched();
        // Schedule transition for changing the dimensions of the leaf
        float randomNumber =
                Constants.FLOAT_RANDOM1 + random.nextFloat() * (Constants.FLOAT_RANDOM2 -
                        Constants.FLOAT_RANDOM1);
                            new ScheduledTask(this,randomNumber,
                                    false,this::angleSched);
        // Schedule transition for changing the angle of the leaf
        float xDimensionDuration = 2.0f;
        new Transition<>(
                this,
                x -> this.setDimensions(new Vector2(x, this.getDimensions().y())),
                Constants.RANDOM_ANGLE1,
                Constants.RANDOM_ANGLE2,
                Transition.LINEAR_INTERPOLATOR_FLOAT,
                xDimensionDuration,
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH,
                null
        );
    }
    /**
     * Schedule a transition for changing the angle of the leaf.
     */
    private void angleSched() {
        float randomStartAngle = -Constants.RANDOM_ANGLE1+ random.nextFloat() * (Constants.ANGEL_RANDOM);

        // Transition for changing the angle of the leaf
        float angleDuration = Constants.DURATION; // Duration of the angle transition in seconds
        new Transition<>(
                this,
                angle -> this.renderer().setRenderableAngle(angle), // Set the renderable angle
                randomStartAngle, // Random start angle
                (randomStartAngle > Constants.ZERO_INIT ? -Constants.RANDOM_ANGLE1 : Constants.RANDOM_ANGLE1),
                Transition.LINEAR_INTERPOLATOR_FLOAT, // Interpolator for linear transition
                angleDuration,
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH, // Transition back and forth
                null
        );
    }

    /**
     * Triggered upon the avatar jumping.
     */
    @Override
    public void UponJump() {
        new Transition<>(
                this,
                angle -> this.renderer().setRenderableAngle(angle),
                Constants.ZERO_FLOAT,
                 Constants.RPTATE,
                Transition.LINEAR_INTERPOLATOR_FLOAT,
                1f,
                Transition.TransitionType.TRANSITION_ONCE, // Transition only once
                null
        );
    }

}