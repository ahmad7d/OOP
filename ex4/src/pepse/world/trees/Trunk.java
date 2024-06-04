package pepse.world.trees;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.components.CoordinateSpace;
import danogl.components.GameObjectPhysics;
import danogl.gui.rendering.OvalRenderable;
import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.ColorSupplier;
import pepse.util.Constants;
import pepse.world.Avatar;

import java.awt.*;
import java.util.Random;
/**
 * Class representing the trunk of a tree in the game.
 */
public class Trunk extends GameObject implements ObserverOfJump{
    /**
     * Constructs a new Trunk instance.
     *
     * @param topLeftCorner Position of the top-left corner of the trunk, in window coordinates (pixels).
     * @param dimensions    Width and height of the trunk in window coordinates.
     * @param renderable    The renderable representing the trunk.
     */
    public Trunk(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);
    }
    /**
     * Handles collision events for the trunk.
     *
     * @param other     The other GameObject involved in the collision.
     * @param collision The collision information.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);

            physics().preventIntersectionsFromDirection(Vector2.ZERO);
            physics().setMass(GameObjectPhysics.IMMOVABLE_MASS);
    }
    /**
     * Performs actions when the avatar jumps.
     */
    @Override
    public void UponJump() {
        Renderable newColor = new RectangleRenderable(ColorSupplier.approximateColor(ColorSupplier
                .approximateColor(Constants.TRUNK_COLOR)));
        renderer().setRenderable(newColor);
    }
}
