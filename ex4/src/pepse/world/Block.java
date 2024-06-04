package pepse.world;

import danogl.GameObject;
import danogl.components.GameObjectPhysics;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.Constants;
/**
 * Class representing a block in the game.
 */
public class Block extends GameObject {

    /**
     * Constructs a new Block instance.
     *
     * @param topLeftCorner The position of the top-left corner of the block.
     * @param renderable    The renderable representing the block.
     */
    public Block(Vector2 topLeftCorner, Renderable renderable) {
        super(topLeftCorner, new Vector2(Constants.BLOCK_SIZE, Constants.BLOCK_SIZE), renderable);
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        physics().setMass(GameObjectPhysics.IMMOVABLE_MASS);
    }
}
