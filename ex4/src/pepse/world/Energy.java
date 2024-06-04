package pepse.world;

import danogl.GameObject;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.Constants;
import danogl.gui.rendering.TextRenderable;
/**
 * Class representing energy in the game.
 */
public class Energy extends GameObject {
    private float energyCounter = Constants.ENERGY_INIT;

    /**
     * Constructs a new Energy instance.
     *
     * @param topLeftCorner The position of the top-left corner of the energy object.
     * @param dimensions    The width and height of the energy object.
     * @param renderable    The renderable representing the energy object.
     */
    public Energy(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);
    }
    /**
     * Updates the energy object based on the elapsed time since the last update.
     * Updates the energy counter display to reflect the current energy counter value.
     *
     * @param deltaTime The time elapsed since the last update, in seconds.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        this.renderer().setRenderable(new TextRenderable(String.format(Constants.ENERGY_FORMATTED_MSG,
                energyCounter)));
    }
    /**
     * Gets the current energy counter value.
     *
     * @return The energy counter value.
     */
    public float getEnergyCounter(){
        return this.energyCounter;
    }

    /**
     * Decreases the energy counter when jumping.
     */
    public void jumpEnergyDecrease(){
        energyCounter -= Constants.JUMP_INCREMENT;
    }

    /**
     * Decreases the energy counter when moving.
     */
    public void moveEnergyDecrease(){
        energyCounter -= Constants.MOVEMENT_DECREMENT;
    }

    /**
     * Increases the energy counter when resting.
     */
    public void restEnergyIncrease(){
        energyCounter += Constants.REST_INCREMENT;
    }

    /**
     * Resets the energy counter to its initial value.
     */
    public void resetEnergyCounter(){
        energyCounter = Constants.ENERGY_INIT;
    }
    /**
     * Increases the energy counter when consuming fruit.
     */
    public void fruitEnergyIncrease(){
        energyCounter += Constants.FRUIT_INCREMENT;
    }

}
