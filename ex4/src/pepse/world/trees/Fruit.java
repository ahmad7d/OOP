package pepse.world.trees;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.components.ScheduledTask;
import danogl.gui.rendering.OvalRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.Constants;
import pepse.world.Avatar;
import pepse.world.Energy;

import java.awt.*;

/**
 * Class representing a fruit in the game.
 */
public class Fruit extends GameObject implements ObserverOfJump{

    private boolean isFruitDisappear;
    private final Energy energyObject;
    private Color fruitsColor = Color.RED;
    /**
     * Constructs a new Fruit instance.
     *
     * @param energyObject   The energy object.
     * @param topLeftCorner  The position of the fruit in window coordinates (pixels).
     * @param dimensions     The width and height of the fruit in window coordinates.
     * @param renderable     The renderable representing the fruit.
     */
    public Fruit(Energy energyObject,
                 Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);
        this.energyObject = energyObject;

    }

    /**
     * Called when a collision with another game object occurs.
     *
     * @param other     The other game object involved in the collision.
     * @param collision The collision information.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if (other.getTag().equals(Constants.AVATAR_TAG) && !isFruitDisappear) {
            isFruitDisappear = true;
            this.energyObject.fruitEnergyIncrease();
            this.renderer().setOpaqueness(Constants.DISAPPEAR_FACTOR);
            new ScheduledTask( other,
            Constants.WAITING_TIME,
            true, this::resetFruitAppearance);
        }
    }


    /**
     * Resets the appearance of the fruit after a certain delay, restoring its default dimensions.
     */
    private void resetFruitAppearance() {
        isFruitDisappear = false;
        this.renderer().setOpaqueness(Constants.APPEAR_FACTOR);
    }

    /**
     * Changes the color of the fruit upon jumping.
     */
    @Override
    public void UponJump() {
        if (this.fruitsColor==Color.RED){
            Renderable colorRenderable = new OvalRenderable(Color.PINK);
            this.renderer().setRenderable(colorRenderable);
            this.fruitsColor=Color.PINK;
        }
        else {
            Renderable colorRenderable = new OvalRenderable(Color.RED);
            this.renderer().setRenderable(colorRenderable);
            this.fruitsColor=Color.RED;
        }
    }
}
