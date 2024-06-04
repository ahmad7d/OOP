package pepse.world.trees;

import danogl.collisions.GameObjectCollection;
import danogl.util.Vector2;
import pepse.util.Constants;
import pepse.world.Avatar;
import pepse.world.Energy;


import java.util.*;
import java.util.function.Function;

/**
 * Class representing flora, including trees and fruits.
 */
public class Flora {

    private Random random;
    private final GameObjectCollection gameObjects;
    private final Energy energyObject;
    private final Avatar avatarObject;
    private Function<Float, Float> function;
    /**
     * Constructs a new Flora instance.
     *
     * @param function     The function defining the landscape.
     * @param seed         The seed for generating random numbers.
     * @param gameObjects  The collection of game objects.
     * @param energyObject The energy object.
     * @param avatarObject The avatar object.
     */
    public Flora(Function<Float, Float> function, int seed, GameObjectCollection gameObjects,
                 Energy energyObject, Avatar avatarObject){

        this.function = function;
        this.random = new Random(seed);
        this.gameObjects = gameObjects;
        this.energyObject = energyObject;
        this.avatarObject = avatarObject;
    }

    /**
     * Creates trees within a specified range.
     *
     * @param minX The minimum X coordinate for tree generation.
     * @param maxX The maximum X coordinate for tree generation.
     */
    public void creatInRange(int minX, int maxX){
        for (float i = minX; i < maxX; i += Constants.BLOCK_SIZE*Constants.TWO) {
            double p = random.nextDouble();
            if (p < Constants.PROBALITY) {
                {
                    int min = Constants.MIN_HEIGHT;
                    int max = Constants.MAX_HEIGHT;

                    int numberOfMultiples = (max - min + Constants.ONE) / Constants.BLOCK_SIZE;
                    int randomIndex = random.nextInt(numberOfMultiples);
                    int heightTrunk = min + (randomIndex * Constants.BLOCK_SIZE);

                    new Tree(new Vector2(i,function.apply(i)-heightTrunk),
                            new Vector2(Constants.BLOCK_SIZE, heightTrunk), gameObjects, energyObject,
                            avatarObject);

                }
            }
        }
    }
}
