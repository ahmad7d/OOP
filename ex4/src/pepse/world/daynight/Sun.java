package pepse.world.daynight;

import danogl.GameObject;
import danogl.components.Transition;
import danogl.gui.rendering.OvalRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.Constants;

import java.awt.*;
/**
 * Class representing the sun GameObject.
 */
public class Sun{

    private static GameObject sunObject;


    /**
     * Creates a sun GameObject with the specified characteristics.
     * The sun represents a bright, luminous object in the sky.
     *
     * @param windowDimensions The dimensions of the game window.
     * @param cycleLength      The duration of a complete rotation cycle of the sun.
     * @return A GameObject representing the sun.
     */
    public static GameObject create(Vector2 windowDimensions,
                                    float cycleLength){

        Vector2 sunLocation = new Vector2(windowDimensions.x() / Constants.TWO,
                windowDimensions.y() * Constants.TWO / Constants.ORBIT_ELLIPSE_B_AXIS);

        Vector2 sunDimensions = Vector2.ONES.mult(Constants.RADIUS_SUN);

        Renderable renderable = new OvalRenderable(Color.YELLOW);


        sunObject = new GameObject(sunLocation, sunDimensions, renderable);

        sunObject.setTag(Constants.SUN_TAG);
        new Transition<>(
                sunObject,
                degree -> updateSunCenter(degree, (windowDimensions.x() / Constants.TWO),
                        (windowDimensions.y() / Constants.TWO)),
                (float) Math.PI / Constants.TWO,
                (float) (Constants.HALF_CYCLE * Math.PI),
                Transition.LINEAR_INTERPOLATOR_FLOAT,
                cycleLength,
                Transition.TransitionType.TRANSITION_LOOP,
                null
        );
        return sunObject;

    }



    /**
     * Updates the center position of the sun based on the current rotation degree.
     *
     * @param degree     The current rotation degree of the sun.
     * @param screenMidX The x-coordinate of the center of the screen.
     * @param screenMidY The y-coordinate of the center of the screen.
     */
    private static void updateSunCenter(float degree, float screenMidX, float screenMidY) {
        float x = (float) (Math.cos(-degree) * Constants.ORBITAL_RADIUS *
                Constants.ORBIT_ELLIPSE_A_AXIS + screenMidX);
        float y = (float) (Math.sin(-degree) * Constants.ORBITAL_RADIUS *
                Constants.ORBIT_ELLIPSE_B_AXIS + screenMidY);
        sunObject.setCenter(new Vector2(x, y));
        }
    }










