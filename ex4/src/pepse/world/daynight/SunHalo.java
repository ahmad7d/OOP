package pepse.world.daynight;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.OvalRenderable;
import pepse.util.Constants;

/**
 * Class representing the halo of the sun.
 */
public class SunHalo {

    /**
     * Creates a sun halo GameObject based on a given sun object.
     * The sun halo is a circular object surrounding the sun, representing its luminous aura.
     *
     * @param sunObject The sun GameObject around which the halo will be created.
     * @return A GameObject representing the sun halo.
     */
    public static GameObject create(GameObject sunObject){

        GameObject sunHaloObject = new GameObject(sunObject.getTopLeftCorner(),
                sunObject.getDimensions().mult(Constants.SUN_HALO_SIZE_ADJUSTMENT),
                new OvalRenderable(Constants.SUNHALO_DEFAULT_COLOR));

        sunHaloObject.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sunHaloObject.setTag(Constants.SUN_HALO_TAG);
        sunHaloObject.addComponent(deltaTime->sunHaloObject.setCenter(sunObject.getCenter()));

        return sunHaloObject;
    }

}
