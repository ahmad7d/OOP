package pepse.world.trees;

import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.OvalRenderable;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.util.Constants;
import pepse.world.Avatar;
import pepse.world.Energy;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *  TREE CLASS
 */
public class Tree {

    // Set to store locations where leaves have been picked
    private final Set<Vector2> pickedLeavesLocations = new HashSet<>();
    // Position of the tree and its height
    private final Vector2 treeLocation;
    private final int treeHeight;
    // Collection of game objects, energy object, and avatar object
    private final GameObjectCollection gameObjects;
    private final Energy energyObject;
    private final Avatar avatarObject;
    // Random generator for fruit creation
    private final Random random = new Random();

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param avatarObject Avatar object
     */
    public Tree(Vector2 topLeftCorner, Vector2 dimensions, GameObjectCollection
            gameObjects, Energy energyObject, Avatar avatarObject) {
        this.treeLocation = topLeftCorner;
        this.treeHeight = (int) dimensions.y();
        this.gameObjects = gameObjects;
        this.energyObject = energyObject;
        this.avatarObject = avatarObject;
        addTreeTrunk();
        addTreeLeaves();
        addTreeFruits();
    }
    /**
     * Adds the trunk of the tree to the game world.
     */
    private void addTreeTrunk() {
        Trunk trunk = new Trunk(treeLocation, new Vector2(Constants.BLOCK_SIZE, treeHeight),
                new RectangleRenderable(Constants.COLOR_TRUNK));
        trunk.setTag(Constants.TRUNK_TAG);
        gameObjects.addGameObject(trunk);
        avatarObject.addJumpObserverToList(trunk);
    }
    /**
     * Adds leaves to the tree in random locations.
     */
    private void addTreeLeaves() {
        int radius = calculateRadius(treeHeight);

        for (int y = (int) (treeLocation.y() - radius); y < treeLocation.y() + radius ;
             y += (Constants.LEAF_SIZE + Constants.TWO)){
            for (int x = (int) (treeLocation.x() - radius); x < treeLocation.x() + radius ;
                 x += (Constants.LEAF_SIZE + Constants.TWO)) {

                double p = random.nextDouble();
                if (p < Constants.RANDOM_NUMBER) {
                    Vector2 leafPosition = new Vector2(x, y);
                    Leaf newLeaf = new Leaf(leafPosition,
                            new Vector2(Constants.LEAF_SIZE, Constants.LEAF_SIZE),
                            new RectangleRenderable(Constants.LEAVES_COLOR), avatarObject);
                    pickedLeavesLocations.add(leafPosition);
                    gameObjects.addGameObject(newLeaf);
                    avatarObject.addJumpObserverToList(newLeaf);
                }

            }
        }
    }
    /**
     * Adds fruits to the tree in random locations.
     */
    private void addTreeFruits() {
        int radius = Math.min(Constants.TREE_MIN, treeHeight * Constants.TREE_MIN / Constants.TREE_MAX);

        for (int y = (int) (treeLocation.y() - radius); y < treeLocation.y() + radius ;
             y += (Constants.LEAF_SIZE + Constants.TWO)){
            for (int x = (int) (treeLocation.x() - radius); x < treeLocation.x() + radius ;
                 x += (Constants.LEAF_SIZE + Constants.TWO)) {

                Vector2 fruitPosition = new Vector2(x, y);
                double p = random.nextDouble();
                if (p < Constants.PROBALITY && !pickedLeavesLocations.contains(fruitPosition)) {

                    Fruit newFruit = new Fruit(energyObject, fruitPosition,
                            new Vector2(Constants.FRUIT_SIZE, Constants.FRUIT_SIZE),
                            new OvalRenderable(Constants.RED_COLOR));
                    newFruit.setTag(Constants.FRUIT_TAG);
                    gameObjects.addGameObject(newFruit);
                    avatarObject.addJumpObserverToList(newFruit);
                }
            }
        }
    }

    /**
     * Calculates the radius of the tree based on its height.
     *
     * @param heightTree The height of the tree.
     * @return The calculated radius of the tree.
     */
    private static int calculateRadius(int heightTree) {

        return Math.min(Constants.TREE_MIN, heightTree * Constants.TREE_MIN / Constants.TREE_MAX);
    }
}
