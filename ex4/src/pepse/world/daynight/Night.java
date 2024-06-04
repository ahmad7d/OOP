package pepse.world.daynight;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.components.Transition;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.util.ColorSupplier;
import pepse.util.Constants;


import java.awt.*;

/**
 * Represents the night sky in the game world.
 * The Night class is responsible for creating and managing the GameObject representing the night sky.
 */
public class Night {


    /**
     * Creates a GameObject representing the night sky with the specified characteristics.
     *
     * @param windowDimensions The dimensions of the game window.
     * @param cycleLength      The duration of a complete transition cycle from midday to midnight and back.
     * @return A GameObject representing the night sky.
     */
    public static GameObject create(Vector2 windowDimensions,
                                    float
                                            cycleLength){
        GameObject nightObject = new GameObject(Vector2.ZERO, windowDimensions,
                new RectangleRenderable(ColorSupplier.approximateColor(Color.BLACK)));

        nightObject.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        nightObject.setTag(Constants.NIGHT_TAG);

        new Transition<>(nightObject, nightObject.renderer()::setOpaqueness,
                Constants.MIDDAY,
                Constants.MIDNIGHT,
                Transition.CUBIC_INTERPOLATOR_FLOAT,
                cycleLength,
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH,
                null);
        return nightObject;
    }

}
